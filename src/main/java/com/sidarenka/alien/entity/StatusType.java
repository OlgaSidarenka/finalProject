package com.sidarenka.alien.entity;

public enum StatusType {
   NEWCOMER(1),EXPERIENCED(2), BLOCKED(3);
    private int statusId;
    StatusType(int statusId){
        this.statusId=statusId;
    }
    public  int getStatusId() {
        return statusId;
    }
    public static StatusType takeStatus(int statusId)    {
        StatusType status=null;
        switch ( statusId) {
            case 1:
                status = StatusType.NEWCOMER;
                break;
            case 2:
                status = StatusType.EXPERIENCED;
                break;
            case 3:
                status = StatusType.BLOCKED;
                break;
        }
        return status;
    }
}
