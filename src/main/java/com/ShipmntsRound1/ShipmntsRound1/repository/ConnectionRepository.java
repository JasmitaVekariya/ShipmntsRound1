package com.ShipmntsRound1.ShipmntsRound1.repository;

import com.ShipmntsRound1.ShipmntsRound1.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection,Integer> {
}
