package tn.bizmatch.authentication.SConfig;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin::read"),
    ADMIN_POST("admin::post"),
    ADMIN_PUT("admin::put"),
    ADMIN_DELETE("admin::delete"),


    CLIENT_READ("client::read"),
    CLIENT_POST("client::post"),
    CLIENT_PUT("client::put"),
    CLIENT_DELETE("client::delete"),


    MANAGER_READ("manager::read"),
    MANAGER_POST("manager::post"),
    MANAGER_PUT("manager::put"),
    MANAGER_DELETE("manager::delete"),


    CONSULTANT_READ("consultant::read"),
    CONSULTANT_POST("consultant::post"),
    CONSULTANT_PUT("consultant::put"),
    CONSULTANT_DELETE("consultant::delete");

    @Getter
    private final String permission;

}
