package com.Banking.Banking.Controller;

import com.Banking.Banking.Entity.Account;
import com.Banking.Banking.Service.AccountService;
import com.Banking.Banking.dto.AccountDTO;
import com.Banking.Banking.dto.ClientDTO;
import com.Banking.Banking.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    //private AccountMapper accountMapper = new AccountMapper();

    @PostMapping("/postAccount")
    public AccountDTO postDetails(@RequestBody AccountDTO accountDTO){
        return accountService.saveDetails(accountDTO);
    }

    @GetMapping("/getAccounts")
    public List<AccountDTO> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<AccountDTO> updateClient(@PathVariable int id, @RequestBody AccountDTO accountDTO) throws Exception {
        AccountDTO accountDTO1 = accountService.updateAccount(id, accountDTO);
        return ResponseEntity.ok(accountDTO1);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable int id) throws Exception{
        accountService.deleteAccount(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAccountById/{id}")
    public AccountDTO getAccountByID(@PathVariable int id) throws Exception {
        return accountService.findById(id);
    }


}
