package com.hospital.hospRgister.controlers;

import com.hospital.hospRgister.entites.City;
import com.hospital.hospRgister.entites.Client;
import com.hospital.hospRgister.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository clientRepo;
    ClientController(ClientRepository clientRepo){
        this.clientRepo = clientRepo;
    }
    @GetMapping("/fetch")
    public List<Client> getAllClients(){
        return clientRepo.findAll();
    }

    @GetMapping("/find/name")
    public ResponseEntity<?> findClientByFullName(String fname, String lname){
        Optional<Client> result =  clientRepo.findClientByFirstNameAndLastName(fname, lname);
        return ResponseEntity.ok(result.isPresent()? result.get(): "Not found!");
    }


    @PostMapping("/save")
    public Client persistClient(String fname, String lname, Integer num){
        Client client = clientRepo.findClientByFirstNameAndLastName(fname,lname)
                .orElse(new Client(fname, lname, num));
        if(client.getId() != null){
            client.setNum(num);
        }
        return clientRepo.save(client);
    }

    @DeleteMapping("/delete")
    public String deleteClient(String fname, String lname){
        Optional<Client> result =  clientRepo.findClientByFirstNameAndLastName(fname, lname);
        if(result.isEmpty()){
            return "Not found!";
        }

        clientRepo.delete(result.get());
        return fname + " " + lname + "was deleted!";
    }
    @GetMapping("/filter")
    public ResponseEntity<?> filterClients(String fname, String lname, int currentPage, int perPage){
        Pageable pageable = PageRequest.of(currentPage - 1, perPage);
        Page<Client> clients = clientRepo.filterClients(pageable, fname.toLowerCase(), lname.toLowerCase());
        Map<String, Object> response = new HashMap<>();
        response.put("TotalElements", clients.getTotalElements());
        response.put("TotalPages", clients.getTotalPages());
        response.put("clients", clients.getContent());

        return ResponseEntity.ok(response);

    }
}

