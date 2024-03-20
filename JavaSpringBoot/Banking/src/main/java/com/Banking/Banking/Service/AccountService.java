package com.Banking.Banking.Service;

import com.Banking.Banking.Entity.Account;
import com.Banking.Banking.Entity.Client;
import com.Banking.Banking.Exception.ResourceNotFoundException;
import com.Banking.Banking.Repository.AccountRepo;
import com.Banking.Banking.Repository.ClientRepo;
import com.Banking.Banking.dto.AccountDTO;
import com.Banking.Banking.mapper.AccountMapper;
import org.hibernate.tool.schema.internal.exec.JdbcConnectionAccessConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private ClientRepo clientRepo;

    private AccountMapper accountMapper = new AccountMapper();

    public AccountDTO saveDetails(AccountDTO accountDTO){
        Account account = new Account();
        account.setBalance(accountDTO.getBalance());
        account.setType(accountDTO.getType());
        Client client = new Client();
        ClientService clientService = new ClientService();
        client  = clientRepo.findById(accountDTO.getClientId()).orElseThrow(() ->
                new ResourceNotFoundException("Client does not exits with id: "+ accountDTO.getClientId()));
        account.setClient(client);
        Account newAccount = accountRepo.save(account);
        return accountMapper.fromEntity(newAccount);

    }

    public List<AccountDTO> getAllAccounts(){
        return accountRepo.findAll()
                .stream()
                .map(accountMapper::fromEntity) // Use a method reference or lambda function here
                .collect(Collectors.toList());
    }

    public AccountDTO updateAccount(int id, AccountDTO accountDTO){
        Account account = accountRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Account does not exist: " + id));

        account.setType(accountDTO.getType());
        account.setBalance(accountDTO.getBalance());

        Client client  = clientRepo.findById(accountDTO.getClientId()).orElseThrow(() ->
                new ResourceNotFoundException("Client does not exits with id: "+ accountDTO.getClientId()));
        account.setClient(client);
        Account newAccount = accountRepo.save(account);
        return accountMapper.fromEntity(newAccount);

    }

    public void deleteAccount (int id){
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account does not exits with id: "+ id));
        accountRepo.delete(account);
    }

    public AccountDTO findById(int id){
        Account account = accountRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Account does not exits with id: "+ id));
        return accountMapper.fromEntity(account);
    }
}

