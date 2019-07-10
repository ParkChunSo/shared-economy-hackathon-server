package com.hackathon.sharedeconomy.model.enums;

/**
 * Created by YoungMan on 2019-02-14.
 */

public enum RoleType {
    ADMIN("관리자"),
    OLD("노인"),
    YOUNG("청년");

    private String roleExplain;

    RoleType(String roleExplain) {
        this.roleExplain = roleExplain;
    }

    public String getRoleExplain() {
        return roleExplain;
    }

    public static RoleType convertRoleType(String role) {
        if (role.equals(OLD.getRoleExplain())) {
            return OLD;
        } else if(role.matches(YOUNG.getRoleExplain())) {
            return YOUNG;
        }
        return ADMIN;
    }
}
