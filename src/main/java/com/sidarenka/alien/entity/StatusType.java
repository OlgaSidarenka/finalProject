package com.sidarenka.alien.entity;

/**
 * The Enum StatusType.
 */
public enum StatusType {
   
   /** The newcomer. */
   NEWCOMER(1),
/** The experienced. */
EXPERIENCED(2), 
 /** The blocked. */
 BLOCKED(3);
    
    /** The status id. */
    private int statusId;
    
    /**
     * Instantiates a new status type.
     *
     * @param statusId the status id
     */
    StatusType(int statusId){
        this.statusId=statusId;
    }
    
    /**
     * Gets the status id.
     *
     * @return the status id
     */
    public  int getStatusId() {
        return statusId;
    }
    
    /**
     * Take status.
     *
     * @param statusId the status id
     * @return the status type
     */
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
