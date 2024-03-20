package com.Banking.Banking.Service;

import com.Banking.Banking.Entity.Client;
import com.Banking.Banking.Exception.ResourceNotFoundException;
import com.Banking.Banking.Repository.ClientRepo;
import com.Banking.Banking.dto.ClientDTO;
import com.Banking.Banking.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
// access through ClientRepo the client entity

    @Autowired
    private ClientRepo clientRepo;

    private ClientMapper clientMapper = new ClientMapper();

    public ClientDTO saveDetails(Client client){
        // save to table
        return clientMapper.fromEntity(clientRepo.save(client));
    }


    public ClientDTO  updateClient(int id, ClientDTO clientDTO){
        Client updateClient = clientRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Client does not exist: " + id));
        Client client = clientMapper.toEntity(clientDTO);

        updateClient.setName(client.getName());
        updateClient.setCnp(client.getCnp());
        //updateClient.setAccounts();
        Client client1= clientRepo.save(updateClient);
        return clientMapper.fromEntity(client1);
    }

    public void deleteClient (int id){
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client does not exits with id: "+ id));
        clientRepo.delete(client);
    }

    public List<ClientDTO> getAllClients(){
        return clientRepo.findAll()
                .stream()
                .map(clientMapper::fromEntity) // Use a method reference or lambda function here
                .collect(Collectors.toList());
    }
    public Client findById(int id){
        Client client = clientRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Client does not exits with id: "+ id));
        return client;
    }
}
