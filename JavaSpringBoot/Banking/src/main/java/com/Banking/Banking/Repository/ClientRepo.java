package com.Banking.Banking.Repository;

import com.Banking.Banking.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepo extends JpaRepository<Client, Integer> {
   // 16:20  https://www.youtube.com/watch?v=8qhaDBCJh6I

    // https://www.youtube.com/watch?v=D1nYkedI0tg&list=PLJVcjChYKnPddYcj70RUZEVDuxNb0_aNA&index=10
}
