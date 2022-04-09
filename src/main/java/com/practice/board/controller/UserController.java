package com.practice.board.controller;

import com.practice.board.dto.ResponseDTO;
import com.practice.board.dto.UserDTO;
import com.practice.board.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){

        try {
            String service = userService.registerUser(userDTO);
            log.info(service);

            List<String> list = new ArrayList<>();
            list.add(service);
            ResponseDTO<String> result = ResponseDTO.<String>builder()
                    .data(list)
                    .build();

            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            String errMsg = e.getMessage();
            ResponseDTO<String> result = ResponseDTO.<String>builder()
                    .error(errMsg)
                    .build();
            return ResponseEntity.badRequest().body(result);
        }

    }
}
