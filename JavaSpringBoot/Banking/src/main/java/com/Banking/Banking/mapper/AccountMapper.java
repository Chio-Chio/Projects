package com.Banking.Banking.mapper;

import com.Banking.Banking.Entity.Account;
import com.Banking.Banking.Entity.Client;
import com.Banking.Banking.Service.ClientService;
import com.Banking.Banking.dto.AccountDTO;
import lombok.Builder;

public class AccountMapper {
    ClientMapper clientMapper = new ClientMapper();
    ClientService clientService = new ClientService();


    public  Account toEntity(AccountDTO dto){
       Account account = new Account();
       account.setType(dto.getType());
       account.setBalance(dto.getBalance());
        System.out.println(dto.getClientId());
       Client client = clientService.findById(dto.getClientId());
       account.setClient(client);
       return account;
    }

    public AccountDTO fromEntity(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setType(account.getType());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setClientId(account.getClient().getId());
        return accountDTO;
    }
}
