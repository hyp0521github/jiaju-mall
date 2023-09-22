package com.yz.furn.entity;

import java.sql.Time;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String created_at;
    private String updated_at;

    public Admin() {
    }

    public Admin(Integer id, String username, String password, String email, String created_at, String updated_at) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
