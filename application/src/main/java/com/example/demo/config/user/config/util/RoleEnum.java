package com.example.demo.config.user.config.util;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {
    ROLE_ADMIN(Arrays.asList(
            RolePermissionEnum.READ_ALL_PRODUCTS,
            RolePermissionEnum.READ_ONE_PRODUCT

    )),
    ROLE_CUSTOMER(Arrays.asList(
            RolePermissionEnum.READ_ALL_PRODUCTS,
                  RolePermissionEnum.READ_ONE_PRODUCT

    ));
    private List<RolePermissionEnum> permissions;

    RoleEnum(List<RolePermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public List<RolePermissionEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermissionEnum> permissions) {
        this.permissions = permissions;
    }
}
