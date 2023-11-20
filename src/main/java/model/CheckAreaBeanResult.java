package model;

import lombok.Getter;
import validator.ValueValidator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static model.AreaResultChecker.getResult;
import static model.Time.getCurrentTimestamp;
@Getter
@Named
@SessionScoped
public class CheckAreaBeanResult implements Serializable {
    private List<CheckAreaBean> resultList;
    private CheckAreaBean newResult;
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext(unitName = "table")
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    public CheckAreaBeanResult(){
        connectToDB();
        loadDB();
        newResult = new CheckAreaBean();
    }
    private void connectToDB() {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("table");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            System.out.println("Connection to database has been established successfully!");
        } catch (Exception e) {
            System.out.println("Connection to database hasn't been established by the reason: " + e.getMessage());
        }
    }
    private void loadDB() {
        connectToDB();
        try {
            entityTransaction.begin();
            resultList = entityManager.createQuery("select d from CheckAreaBean d", CheckAreaBean.class).getResultList();
            entityTransaction.commit();
            System.out.println("Data has been loaded successfully.");
            entityManager.close();
        } catch (Exception e) {
            System.out.println("Data loading error: " + e.getMessage());
        }
    }
    public void addResult(Type type) {
        connectToDB();
        try {
            entityTransaction.begin();
            long startTime = System.nanoTime();
            ValueValidator validator = new ValueValidator();
            newResult.setType(type);
            if(validator.validate(newResult.getX(), newResult.getY(), newResult.getR(), newResult.getType())){
                newResult.setResult(getResult(newResult.getX(), newResult.getY(), newResult.getR()));
                newResult.setExecutionTime((System.nanoTime() - startTime) / 1000);
                newResult.setCurrentTime(getCurrentTimestamp());
                if (resultList == null){

                }
                resultList.add(newResult);
                System.out.println("New result " + newResult);
                entityManager.persist(newResult);
                entityTransaction.commit();
                FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(
                        "resultSet(" + newResult.getX() + ", " + newResult.getY() +
                                ", " + newResult.getR() + ", " + newResult.getResult() + ");");
            }

            // Очистка newResult для следующего ввода
            newResult = new CheckAreaBean();

        } catch (Exception e){
            System.out.println("Result adding error: " + e.getMessage());
        }

    }
    public void clearResults() {
        connectToDB();
        newResult = new CheckAreaBean();
        try {
            entityTransaction.begin();
            entityManager.createQuery("DELETE FROM CheckAreaBean ", CheckAreaBean.class).executeUpdate();
            resultList.clear();
            entityTransaction.commit();
            System.out.println("Results cleaned successfully!");
            entityManager.close();
            FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add("clear();");
        } catch (Exception e) {
            System.out.println("Results cannot be cleaned: " + e.getMessage());
        }
    }
    public void setResultList(List<CheckAreaBean> resultList){
        this.resultList = resultList;
    }
    public void setNewResult(CheckAreaBean newResult){
        this.newResult = newResult;
    }

}
