package com.cabletvbackend.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="userdeatais", schema = "CableTVUserDetails")
public class Userdatails {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
