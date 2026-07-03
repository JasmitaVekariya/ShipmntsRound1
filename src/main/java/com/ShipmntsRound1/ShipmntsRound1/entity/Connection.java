package com.ShipmntsRound1.ShipmntsRound1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private Integer fromUserId;

    @Column(nullable = false)
    private ConnectionStatus status;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = true)
    private LocalDateTime acceptedAt;

    @Column(nullable = true)
    private LocalDateTime rejectedAt;

    @ManyToOne
    @JoinColumn(name = "toUserId")
    private User toUser;
}
