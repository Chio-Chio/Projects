package com.Banking.Banking.Controller;

import com.Banking.Banking.Entity.Client;
import com.Banking.Banking.Repository.AccountRepo;
import com.Banking.Banking.Repository.ClientRepo;
//import com.Banking.Banking.dto.ClientRequest;
import com.Banking.Banking.Service.AccountService;
import com.Banking.Banking.Service.ClientService;
import com.Banking.Banking.dto.ClientDTO;
import com.Banking.Banking.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController{
   @Autowired
    private ClientService clientService;


//
//   // every path will have a function
//    @PostMapping("/addClient")
//    public Client postDetails(@RequestBody Client client){
//    // by @requestBody data from postman will be fetch by this fct
//        return clientService.saveDetails(client);
//    }
//    @PostMapping("/createAccount")
//    public Client createAccount(@RequestBody ClientRequest clientRequest){
//        return clientService.saveCreateAccount(clientRequest.getClient());
//    }
//
//    public List<Client> findAllAccounts(){
//        return client
//    }

    private ClientMapper clientMapper = new ClientMapper();

    @PostMapping("/createClient")
    public ClientDTO createClient(@RequestBody ClientDTO clientDto){
        return clientService.saveDetails(clientMapper.toEntity(clientDto));
    }

    @GetMapping("/getClients")
    public List<ClientDTO> getAllClients(){
        return clientService.getAllClients();
    }

    //get client by id
    @GetMapping("/getClientById{id}")
    public ResponseEntity<ClientDTO> getClientByID(@PathVariable int id) throws Exception{
        ClientDTO clientDTO = clientMapper.fromEntity(clientService.findById(id));
        return ResponseEntity.ok(clientDTO);

    }

    //update
    @PutMapping("/updateClient/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable int id, @RequestBody ClientDTO clientDTO) throws Exception {
        ClientDTO clientDTO1 = clientService.updateClient(id, clientDTO);
        return ResponseEntity.ok(clientDTO1);

    }

    // delete
    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable int id) throws Exception{
        clientService.deleteClient(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
