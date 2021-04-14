package io.spring.vikop.user;

public enum RoleType {
    ROLE_USER("user"),
    ROLE_ADMIN("admin");


    private String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
