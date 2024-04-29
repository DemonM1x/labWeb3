package validator;

import model.Type;

import java.math.BigDecimal;

public class ValueValidator {
    private final BigDecimal[] xValues = {BigDecimal.valueOf(-4),BigDecimal.valueOf(-3), BigDecimal.valueOf(-2), BigDecimal.valueOf(-1),
            BigDecimal.valueOf(0), BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3), BigDecimal.valueOf(4)};

    public boolean validate(BigDecimal x, BigDecimal y, BigDecimal r, Type type){
        if (type == Type.SUBMIT) {
            return validateSubmit(x, y, r);
        }
        else if (type == Type.CLICK){
            return validateClick(x, y, r);
        }
        return false;
    }
    private boolean validateClick(BigDecimal x, BigDecimal y, BigDecimal r){
        return validateClickR(r) && validateClickX(x, r) && validateClickY(y, r);
    }

    private boolean validateSubmit(BigDecimal x, BigDecimal y, BigDecimal r){
        return validateSubmitX(x) && validateSubmitY(y) && validateSubmitR(r);
    }
    private boolean validateSubmitX(BigDecimal x){
        for(BigDecimal value : xValues){
            if(x.compareTo(value) == 0){
                return true;
            }
        }
        return false;
    }
    private boolean validateSubmitY(BigDecimal y){
        return y.compareTo(BigDecimal.valueOf(-3)) >= 0 && y.compareTo(BigDecimal.valueOf(5)) <= 0  ;
    }

    private boolean validateSubmitR(BigDecimal r){
        return r.compareTo(BigDecimal.valueOf(1)) >= 0 && r.compareTo(BigDecimal.valueOf(3)) <= 0 && (r.doubleValue() % 0.5) % 1  == 0;
    }
    private boolean validateClickX(BigDecimal x, BigDecimal r){
        return -1.25*r.doubleValue() <= x.doubleValue() && x.doubleValue() <= 1.25*r.doubleValue();
    }

    private boolean validateClickY(BigDecimal y, BigDecimal r){
        return -1.25*r.doubleValue() <= y.doubleValue() && y.doubleValue() <= 1.25*r.doubleValue();
    }

    private boolean validateClickR(BigDecimal r){
        return validateSubmitR(r);
    }
}

