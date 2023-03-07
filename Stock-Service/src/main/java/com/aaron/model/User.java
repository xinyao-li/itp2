package com.aaron.model;

import javax.persistence.*;

@Entity
@Table(name = "login_table")
public class User {

    @Id
    private Integer id;
    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public void setId(Integer id){this.id = id;}

    public Integer getId() {return id;}

    public void setUsername(String username){this.username = username;}

    public String getUsername(){return username;}

    public void setPassword(String password){this.password = password;}

    public String getPassword(){return password;}
}
