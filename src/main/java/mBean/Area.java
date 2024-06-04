package mBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ApplicationScoped
public class Area implements Serializable, AreaMBean {
    private double area;

    public Area(){

    }

    @Override
    public void calculateArea(double r) {
        area = (r * r/2) + (Math.PI * Math.pow(r, 2) / 4) + (r/2 * r/2);
    }

    @Override
    public double getArea() {
        return area;
    }
}
