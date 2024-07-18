package com.auth.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Token extends BaseClass{

    private String value;
    private Date expiryDate;
    private Boolean isDeleted;
    @ManyToOne
    private User user;
}
