package model;

import java.math.BigDecimal;

public class AreaResultChecker {

    public static BigDecimal getArea(BigDecimal x1, BigDecimal y1, BigDecimal x2, BigDecimal y2, BigDecimal x3, BigDecimal y3){
        return ((x1.multiply(y2.subtract(y3))).add(x2.multiply(y3.subtract(y1))).add(x3.multiply(y1.subtract(y2)))).divide(BigDecimal.valueOf(2)).abs();
    }
    public static boolean getResult(BigDecimal x, BigDecimal y, BigDecimal r){
        return isSecondSection(x, y, r) || isThirdSection(x, y, r) || isForthSection(x, y, r);
    }
    private static boolean isSecondSection(BigDecimal x, BigDecimal y, BigDecimal r){
        return x.compareTo(BigDecimal.ZERO) <= 0 && y.compareTo(BigDecimal.ZERO) >= 0 &&
                x.abs().compareTo(r) <= 0 && y.abs().compareTo(r.divide(BigDecimal.valueOf(2) )) <= 0;
    }
    private static boolean isThirdSection(BigDecimal x, BigDecimal y, BigDecimal r){
        return x.compareTo(BigDecimal.ZERO) <= 0 && y.compareTo(BigDecimal.ZERO) <= 0 &&
                x.pow(2).add(y.pow(2)).compareTo(r.pow(2)) <= 0;
    }
    private static boolean isForthSection(BigDecimal x, BigDecimal y, BigDecimal r){
        BigDecimal A = getArea(BigDecimal.ZERO, BigDecimal.ZERO, r.divide(BigDecimal.valueOf(2)), BigDecimal.ZERO, BigDecimal.ZERO, r.negate());
        BigDecimal A1 = getArea(x, y, r.divide(BigDecimal.valueOf(2)), BigDecimal.ZERO, BigDecimal.ZERO, r.negate());
        BigDecimal A2 = getArea(BigDecimal.ZERO, BigDecimal.ZERO, x, y, BigDecimal.ZERO, r.negate());
        BigDecimal A3 = getArea(BigDecimal.ZERO, BigDecimal.ZERO, r.divide(BigDecimal.valueOf(2)), BigDecimal.ZERO, x, y);
        return x.compareTo(BigDecimal.ZERO) >= 0 && y.compareTo(BigDecimal.ZERO) <= 0 &&
                A.compareTo(A1.add(A2).add(A3)) == 0;
    }
}
