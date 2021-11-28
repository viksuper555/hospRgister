package com.hospital.hospRgister.repositories;

import com.hospital.hospRgister.entites.Client;
import org.springframework.data.domain.Page;
import  org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByFirstNameAndLastName(String fname, String lname);

    @Query("SELECT c " +
            "FROM Client c " +
            "WHERE " +
            "lower(c.firstName) " +
            "LIKE :#{#firstName == null || #firstName.isEmpty()? '%' : #firstName + '%'} " +
            "AND lower(c.lastName)" +
            "LIKE  :#{#lastName == null || #lastName.isEmpty()? '%' : #lastName + '%'}"

    )
    Page<Client> filterClients(Pageable pageable, String firstName, String lastName);

    //Client findClientByFirstNameAndLastName(String fname, String lname);
}
