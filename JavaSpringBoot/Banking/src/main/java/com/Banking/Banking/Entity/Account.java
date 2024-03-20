package com.Banking.Banking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // correspond to particular table in db
@Data // automatically create all the getters and setters
@Table(name = "Account")// reference table name in sql db
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(name = "ID")
   // @GeneratedValue
    private int id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "BALANCE")
    private int balance;


    @ManyToOne
    @JoinColumn (name = "cl_fk", nullable = false)
    private Client client;
}
