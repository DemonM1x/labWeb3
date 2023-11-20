package model;

import java.math.BigDecimal;

public class AreaResultChecker {
    public static boolean getResult(BigDecimal x, BigDecimal y, BigDecimal r){
        return isSecondSection(x, y, r) || isThirdSection(x, y, r) || isForthSection(x, y, r);
    }
    private static boolean isSecondSection(BigDecimal x, BigDecimal y, BigDecimal r){
        return x.compareTo(BigDecimal.ZERO) <= 0 && y.compareTo(BigDecimal.ZERO) >= 0 &&
                x.abs().compareTo(r) >= 0 && y.abs().compareTo(r.divide(BigDecimal.valueOf(2) )) <= 0;
    }
    private static boolean isThirdSection(BigDecimal x, BigDecimal y, BigDecimal r){
        return x.compareTo(BigDecimal.ZERO) <= 0 && y.compareTo(BigDecimal.ZERO) <= 0 &&
                x.pow(2).add(y.pow(2)).compareTo(r.pow(2)) <= 0;
    }
    private static boolean isForthSection(BigDecimal x, BigDecimal y, BigDecimal r){
        return x.compareTo(BigDecimal.ZERO) >= 0 && y.compareTo(BigDecimal.ZERO) <= 0 &&
                y.compareTo(r.negate().add(x.multiply(BigDecimal.valueOf(0.5)))) >= 0;
    }
}
