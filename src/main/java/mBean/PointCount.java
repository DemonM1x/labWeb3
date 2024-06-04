package mBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;

@Named
@ApplicationScoped
public class PointCount extends NotificationBroadcasterSupport implements Serializable, PointCountMBean {

    private long pointCount = 0;
    private long correctPoint = 0;
    private int sequenceNumber = 0;

    public PointCount(){

    }


    @Override
    public void updatePointCount() {
        pointCount++;
    }

    @Override
    public void updateCorrectPoint() {
        correctPoint++;
    }

    @Override
    public void MissPlace() {
        Notification notification = new Notification
                ( "Point miss place", getClass().getSimpleName(), sequenceNumber++, "Point miss correct place" );
        sendNotification(notification);
    }

    @Override
    public long getCorrectPoint() {
        return correctPoint;
    }

    @Override
    public long getPointCount() {
        return pointCount;
    }
    @Override public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[] { AttributeChangeNotification.ATTRIBUTE_CHANGE };
        String name = AttributeChangeNotification.class.getName();
        String description = "Miss notification";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[] { info };
    }
}
