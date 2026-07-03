package com.ShipmntsRound1.ShipmntsRound1.controller;

import com.ShipmntsRound1.ShipmntsRound1.dto.UserConnectionReq;
import com.ShipmntsRound1.ShipmntsRound1.dto.UserConnectionResponse;
import com.ShipmntsRound1.ShipmntsRound1.service.ConnectionService;
import com.ShipmntsRound1.ShipmntsRound1.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connect")
public class ConnectionController {
    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PostMapping
    public UserConnectionResponse addConnection(@RequestBody UserConnectionReq u, @RequestHeader(name = "current_user_id") Integer fromUserId)
    {
        return connectionService.ConnectionReq(u, fromUserId);
    }


}
