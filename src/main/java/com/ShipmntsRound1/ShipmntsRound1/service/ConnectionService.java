package com.ShipmntsRound1.ShipmntsRound1.service;

import com.ShipmntsRound1.ShipmntsRound1.dto.*;
import com.ShipmntsRound1.ShipmntsRound1.entity.Connection;
import com.ShipmntsRound1.ShipmntsRound1.entity.ConnectionStatus;
import com.ShipmntsRound1.ShipmntsRound1.entity.User;
import com.ShipmntsRound1.ShipmntsRound1.repository.ConnectionRepository;
import com.ShipmntsRound1.ShipmntsRound1.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final UserRepository userRepository;

    public ConnectionService(ConnectionRepository connectionRepository, UserRepository userRepository) {
        this.connectionRepository = connectionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserConnectionResponse ConnectionReq(UserConnectionReq toUser, Integer fromUserId)
    {
        User user = userRepository.findById(toUser.getToUserId())
                .orElseThrow(()-> new RuntimeException("No user found with id u1"));
        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new RuntimeException("No user found with id u2"));

        if(user.getId().equals(fromUserId))
            throw new RuntimeException("A user cannot connect with themselves");

        List<Connection> connections = user.getConnections();

        for(Connection c : connections)
        {
            if(c.getFromUserId().equals(fromUserId))
                throw new RuntimeException("These users are already connected");
        }

        Connection c = new Connection();
        c.setFromUserId(fromUserId);
        c.setToUser(user);
        c.setStatus(ConnectionStatus.PENDING);
        user.addConnection(c);

        userRepository.save(user);

        return new UserConnectionResponse(c.getId(), c.getFromUserId(), c.getToUser().getId());
    }

    @Transactional
    public ConnectionResponsRes connectionRespond(ConnectionResponsReq c, Integer userId)
    {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User is not found"));
        if(c == null)
            throw new RuntimeException("connectionId and action are required");

        if(!c.getAction().equals("ACCEPT"))
            throw new RuntimeException("action must be either ACCEPT or REJECT");

        Connection connection = connectionRepository.findById(c.getConnectionId())
                .orElseThrow(()-> new RuntimeException("No connection request found with id c1"));

        if(!connection.getStatus().equals(ConnectionStatus.PENDING))
            throw new RuntimeException("Only pending connection requests can be responded to");

        if(!user.getId().equals(connection.getToUser().getId()))
            throw new RuntimeException("Only the receiver of the connection request can respond to it");
        boolean isAccept = false;
        if(c.getAction().equals("ACCEPT")) {
            connection.setStatus(ConnectionStatus.ACCEPTED);
            connection.setAcceptedAt(LocalDateTime.now());
            isAccept = true;
        }
        else {
            connection.setStatus(ConnectionStatus.REJECTED);
            connection.setRejectedAt(LocalDateTime.now());
        }
        connectionRepository.save(connection);

        return   new ConnectionResponsRes(
                connection.getId(),
                connection.getFromUserId(),
                connection.getToUser().getId(),
                connection.getStatus(),
                connection.getCreatedAt(),
                connection.getAcceptedAt(),
                connection.getRejectedAt()
        );
    }

}
