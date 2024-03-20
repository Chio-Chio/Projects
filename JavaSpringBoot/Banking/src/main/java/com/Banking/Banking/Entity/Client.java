package com.Banking.Banking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity // correspond to particular table in db
@Data // automatically create all the getters and setters
@Table(name = "Client")// reference table name in sql db
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    // class representation as a table
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CNP")
    private String cnp;

//    @OneToMany(targetEntity = Account.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "ca_fk", referencedColumnName = "id") // id form client
//    private List<Account> accounts;

}
