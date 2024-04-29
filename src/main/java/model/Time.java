package model;

import java.sql.Timestamp;
import java.util.Date;

public class Time {
    public static Timestamp getCurrentTimestamp() {
        Date currentDate = new Date();
        return new Timestamp(currentDate.getTime());
    }
}
