package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
@Table(name = "CheckAreaBean")
@Entity
@Getter
@Setter
public class CheckAreaBean implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY  )
    private Long id;
    @Column(name = "x")
    private BigDecimal x;
    @Column(name = "y")
    private BigDecimal y;
    @Column(name = "r")
    private BigDecimal r = BigDecimal.valueOf(1.0);
    @Column(name = "result")
    private Boolean result;
    @Column(columnDefinition = "timestamp without time zone default now()", name = "currentTime")
    private Timestamp currentTime;
    @Column(name = "executionTime")
    private long executionTime;
    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private Type type;
    public CheckAreaBean() {
        super();
    }
    public CheckAreaBean(BigDecimal x, BigDecimal y, BigDecimal r, boolean result, Timestamp currentTime, long executionTime){
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.currentTime = currentTime;
        this.executionTime  = executionTime;
    }



    @Override
    public String toString() {
        return "CheckAreaBean{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", result=" + result +
                ", currentTime=" + currentTime +
                ", executionTime=" + executionTime +
                '}';
    }

}
