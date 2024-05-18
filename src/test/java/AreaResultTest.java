import model.AreaResultChecker;
import model.CheckAreaBean;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AreaResultTest {
    private CheckAreaBean newResult;

    @Test
    public void topLeft(){
        newResult = new CheckAreaBean();
        newResult.setR(BigDecimal.valueOf(5));
        for (double i = -5; i <= 0; i += 0.5) {
            for (double j = 0; j <= 2.5; j += 0.5) {
                newResult.setX(BigDecimal.valueOf(i));
                newResult.setY(BigDecimal.valueOf(j));
                newResult.setResult(AreaResultChecker.getResult(newResult.getX(), newResult.getY(), newResult.getR()));
                assertEquals(newResult.toString(), "has a point X: " + i + " Y: " + j + " Result: " + "true");
            }
        }
    }
    @Test
    public void topLeftLeft() {
        newResult = new CheckAreaBean();
        newResult.setR(BigDecimal.valueOf(5));

        for (double i = -7; i <= -3; i += 0.5) {
            for (double j = 3; j <= 5; j += 0.5) {
                newResult.setX(BigDecimal.valueOf(i));
                newResult.setY(BigDecimal.valueOf(j));
                newResult.setResult(AreaResultChecker.getResult(newResult.getX(), newResult.getY(), newResult.getR()));
                assertEquals(newResult.toString(), "has a point X: " + i + " Y: " + j + " Result: " + "false");
            }
        }
    }
    @Test
    public void topRight(){
        newResult = new CheckAreaBean();
        newResult.setR(BigDecimal.valueOf(5));

        for (double i = 0.1; i <= 3; i += 0.5) {
            for (double j = 0.1; j <= 5; j += 0.5) {
                newResult.setX(BigDecimal.valueOf(i));
                newResult.setY(BigDecimal.valueOf(j));
                newResult.setResult(AreaResultChecker.getResult(newResult.getX(), newResult.getY(), newResult.getR()));
                assertEquals(newResult.toString(), "has a point X: " + i + " Y: " + j + " Result: " + "false");
            }
        }
    }
    @Test
    public void downLeft(){
        newResult = new CheckAreaBean();
        newResult.setR(BigDecimal.valueOf(5));

        for (double i = -3; i <= 0; i += 0.5) {
            for (double j = 0; j >= -3; j -= 0.5) {
                newResult.setX(BigDecimal.valueOf(i));
                newResult.setY(BigDecimal.valueOf(j));
                newResult.setResult(AreaResultChecker.getResult(newResult.getX(), newResult.getY(), newResult.getR()));
                assertEquals(newResult.toString(), "has a point X: " + i + " Y: " + j + " Result: " + "true");
            }
        }
    }
    @Test
    public void downRight(){
        newResult = new CheckAreaBean();
        newResult.setR(BigDecimal.valueOf(5));

        for (double i = 0; i <= 1.5; i += 0.5) {
            for (double j = -1.5 + i; j <= 0; j += 0.5) {
                newResult.setX(BigDecimal.valueOf(i));
                newResult.setY(BigDecimal.valueOf(j));
                newResult.setResult(AreaResultChecker.getResult(newResult.getX(), newResult.getY(), newResult.getR()));
                assertEquals(newResult.toString(), "has a point X: " + i + " Y: " + j + " Result: " + "true");
            }
        }
    }

}

