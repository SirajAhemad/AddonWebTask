package com.example.Addon.AddonWeb.entity;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String username;

    @Column
    private Long mobileNo;

    @Column
    private String email;

    @Column
    private String address;

    @Column
    private String imageUrl;


}



