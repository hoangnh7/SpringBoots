package com.example.getrequest.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="identity_card")

public class IdentityCard {
    @Id
    private String id;

    @Column(name="expired")
    private Date expired;

    @Column(name="issued")
    private Date issued;

}
