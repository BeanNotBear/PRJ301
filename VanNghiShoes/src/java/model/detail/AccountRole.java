/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.detail;

/**
 *
 * @author nghin
 */
public enum AccountRole {
    ADMIN("admin"),
    CUSTOMER("customer"),
    SUPPER_ADMIN("super-admin");

    private final String label;

    private AccountRole(String label) {
        this.label = label;
    }

    public static AccountRole create(int role) {
        switch (role) {
            case 1:
                return ADMIN;
            case 2:
                return CUSTOMER;
            case 3:
                return SUPPER_ADMIN;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return label;
    }
}
