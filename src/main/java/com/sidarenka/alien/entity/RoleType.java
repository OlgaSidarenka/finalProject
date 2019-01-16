package com.sidarenka.alien.entity;

// TODO: Auto-generated Javadoc
/**
 * The Enum RoleType.
 */
public enum RoleType {
    
    /** The user. */
    USER(2), 
 /** The admin. */
 ADMIN(1);
    
    /** The role id. */
    private long roleId;
    
    /**
     * Instantiates a new role type.
     *
     * @param roleId the role id
     */
    RoleType(long roleId){
        this.roleId = roleId;
    }
    
    /**
     * Gets the role id.
     *
     * @return the role id
     */
    public long getRoleId() {
        return roleId;
    }
    
    /**
     * Take role.
     *
     * @param roleId the role id
     * @return the role type
     */
    public static RoleType takeRole(long roleId) {
        return roleId == 2 ? USER : ADMIN;
    }
}
