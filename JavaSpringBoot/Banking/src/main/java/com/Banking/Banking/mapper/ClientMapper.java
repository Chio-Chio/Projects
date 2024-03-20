package com.Banking.Banking.mapper;
import com.Banking.Banking.mapper.AccountMapper;
import com.Banking.Banking.Entity.Client;
import com.Banking.Banking.dto.ClientDTO;

import java.util.stream.Collectors;

public class ClientMapper {
    public Client toEntity(ClientDTO dto){
        Client client = new Client();
        client.setName(dto.getName());
        client.setCnp(dto.getCnp());
       // client.setAccounts(dto.getAccounts().stream().map(AccountMapper::toEntity).collect(Collectors.toList()));

        return client;
    }

    public ClientDTO fromEntity(Client client){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName(client.getName());
        clientDTO.setCnp(client.getCnp());
       // clientDTO.setAccounts(client.getAccounts());
        return clientDTO;
    }
}
