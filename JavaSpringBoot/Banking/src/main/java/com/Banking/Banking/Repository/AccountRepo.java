package com.Banking.Banking.Repository;

import com.Banking.Banking.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Integer> {
}
