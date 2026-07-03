package com.ShipmntsRound1.ShipmntsRound1.service;

import com.ShipmntsRound1.ShipmntsRound1.dto.UserConnectionReq;
import com.ShipmntsRound1.ShipmntsRound1.dto.UserConnectionResponse;
import com.ShipmntsRound1.ShipmntsRound1.entity.User;
import com.ShipmntsRound1.ShipmntsRound1.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(User u)
    {
        if(u == null)
            throw new RuntimeException("name and email are required");

        if(userRepository.findByEmail(u.getEmail()) != null)
            throw new RuntimeException("A user with this email already exists");

        User user = userRepository.save(u);
        if(user == null)
            throw new RuntimeException("Something went wrong, please try again");
        return user;
    }

    public UserConnectionResponse ConnectionReq(UserConnectionReq toUser, Integer fromUser)
    {
        User user =
    }

}
