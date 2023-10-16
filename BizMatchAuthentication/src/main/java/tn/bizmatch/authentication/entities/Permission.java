package tn.bizmatch.authentication.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin::read"),
    ADMIN_POST("admin::post"),
    ADMIN_PUT("admin::put"),
    ADMIN_DELETE("admin::delete"),

    CRepresentative_READ("cRepresentative::read"),
    CRepresentative_POST("cRepresentative::post"),
    CRepresentative_PUT("cRepresentative::put"),
    CRepresentative_DELETE("cRepresentative::delete"),


    Investor_READ("investor::read"),
    Investor_POST("investor::post"),
    Investor_PUT("investor::put"),
    Investor_DELETE("investor::delete"),

    Collaborator_READ("collaborator::read"),
    Collaborator_POST("collaborator::post"),
    Collaborator_PUT("collaborator::put"),
    Collaborator_DELETE("collaborator::delete"),

    Entrepreneur_READ("entrepreneur::read"),
    Entrepreneur_POST("entrepreneur::post"),
    Entrepreneur_PUT("entrepreneur::put"),
    Entrepreneur_DELETE("entrepreneur::delete");



    @Getter
    private final String permission;

}
