package com.ShipmntsRound1.ShipmntsRound1.dto;

import com.ShipmntsRound1.ShipmntsRound1.entity.ConnectionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ConnectionResponsRes {
    private Integer id;
    private Integer fromUserId;
    private Integer toUserId;
    private ConnectionStatus status;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime rejectedAt;
    private LocalDateTime acceptedAt;
}
