/*
 * This file is generated by jOOQ.
*/
package com.ducnd.mysql.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User implements Serializable {

    private static final long serialVersionUID = -1502007327;

    private Integer id;
    private String  username;
    private String  password;
    private String  token;

    public User() {}

    public User(User value) {
        this.id = value.id;
        this.username = value.username;
        this.password = value.password;
        this.token = value.token;
    }

    public User(
        Integer id,
        String  username,
        String  password,
        String  token
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User (");

        sb.append(id);
        sb.append(", ").append(username);
        sb.append(", ").append(password);
        sb.append(", ").append(token);

        sb.append(")");
        return sb.toString();
    }
}
