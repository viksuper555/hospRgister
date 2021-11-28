package com.hospital.hospRgister.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import javax.print.DocFlavor;
import java.util.Set;


@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private Set<Client> clients;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
