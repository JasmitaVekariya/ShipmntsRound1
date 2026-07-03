package com.ShipmntsRound1.ShipmntsRound1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserConnectionResponse {
    private Integer connectionId;
    private Integer fromUserId;
    private Integer toUserId;
}
