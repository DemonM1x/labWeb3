package model;

import db.DAOFactory;
import lombok.Getter;
import mBean.Area;
import mBean.PointCount;
import validator.ValueValidator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.LinkedList;

import static model.AreaResultChecker.getResult;
import static model.Time.getCurrentTimestamp;
@Getter
@Named
@SessionScoped
public class
CheckAreaBeanResult implements Serializable {
    private LinkedList<CheckAreaBean> resultList;
    private CheckAreaBean newResult;
    @Inject
    private PointCount pointCount;
    @Inject
    private Area area;
    public CheckAreaBeanResult(){
        loadDB();
        newResult = new CheckAreaBean();
    }
    @Named(value = "resultList")
    public LinkedList<CheckAreaBean> getResults() {
        return resultList;
    }
    private void loadDB() {
        try {
            resultList = new LinkedList<>(DAOFactory.getInstance().getResultDAO().getAllResults());
        } catch (Exception e) {
            System.out.println("Data loading error: " + e.getMessage());
        }
    }
    public void addResult(Type type) {

        try {

            long startTime = System.nanoTime();
            ValueValidator validator = new ValueValidator();
            if (validator.validate(newResult.getX(), newResult.getY(), newResult.getR(), type)) {
                newResult.setType(type);
                newResult.setResult(getResult(newResult.getX(), newResult.getY(), newResult.getR()));
                newResult.setExecutionTime((System.nanoTime() - startTime) / 1000);
                newResult.setCurrentTime(getCurrentTimestamp());
                DAOFactory.getInstance().getResultDAO().addNewResult(newResult);
                resultList.add(newResult);
                pointCount.updatePointCount();
                area.calculateArea(newResult.getR().doubleValue());

                if (newResult.getResult()){
                    pointCount.updateCorrectPoint();
                }
                else {
                    pointCount.MissPlace();
                }
                System.out.println("New result " + newResult);
                FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(
                        "resultSet("+ newResult.getX() +", "+ newResult.getY() +","+ newResult.getR()+","+ newResult.getResult() +");");
            }
            // Очистка newResult для следующего ввода
            newResult = new CheckAreaBean();

        } catch (Exception e){
            System.out.println("Result adding error: " + e.getMessage());
        }

    }
    public void clearResults() {

        newResult = new CheckAreaBean();
        try {
            DAOFactory.getInstance().getResultDAO().clearResults();
            resultList.clear();
            System.out.println("Results cleaned successfully!");
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (Exception e) {
            System.out.println("Results cannot be cleaned: " + e.getMessage());
        }
    }
    public void setResultList(LinkedList<CheckAreaBean> resultList){
        this.resultList = resultList;
    }
    public void setNewResult(CheckAreaBean newResult){
        this.newResult = newResult;
    }

}
