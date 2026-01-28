package com.advance.data;

import com.advance.common.entity.User;
import com.advance.common.enums.UserRoleEnum;

public class UserFixture {

    public static String DEFAULT_USERNAME = "김수민";
    public static String DEFAULT_PASSWORD = "1234";
    public static String DEFAULT_EMAIL = "test@test.com";

    public static User createUserAdminRole() {
        return new User(DEFAULT_USERNAME, DEFAULT_PASSWORD, DEFAULT_EMAIL, UserRoleEnum.ADMIN);
    }

    public static User createUserNormalRole() {
        return new User(DEFAULT_USERNAME, DEFAULT_PASSWORD, DEFAULT_EMAIL, UserRoleEnum.NORMAL);
    }
}
