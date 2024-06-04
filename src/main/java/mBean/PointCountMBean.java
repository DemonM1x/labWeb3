package mBean;

import javax.management.MBeanNotificationInfo;

public interface PointCountMBean {
    void updatePointCount();
    void updateCorrectPoint();

    void MissPlace();

    long getCorrectPoint();
    long getPointCount();
    MBeanNotificationInfo[] getNotificationInfo();
}
