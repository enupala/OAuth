package com.auth.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Role extends BaseClass{
    private String roleName;
}
