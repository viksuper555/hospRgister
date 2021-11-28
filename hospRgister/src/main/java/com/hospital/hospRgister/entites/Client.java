package com.hospital.hospRgister.entites;

import javax.persistence.*;

import javax.print.DocFlavor;


@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column()
    private String lastName;

    @Column()
    private Integer num;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getNum() {
        return num;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Client(){

    }
    public Client(String firstName, String lastName, Integer num) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.num = num;
    }

}
