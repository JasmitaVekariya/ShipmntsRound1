package com.ShipmntsRound1.ShipmntsRound1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "connections")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer fromUserId;

    @OneToMany
    @JoinColumn(name = "toUserId")
    private User toUser;
}
