package com.ShipmntsRound1.ShipmntsRound1.repository;

import com.ShipmntsRound1.ShipmntsRound1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByEmail(String email);
}
