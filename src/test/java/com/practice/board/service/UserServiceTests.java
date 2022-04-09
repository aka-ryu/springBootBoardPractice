package com.practice.board.service;

import com.practice.board.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;


    @Test
    public void userRegisterTest(){

            UserDTO userDTO = UserDTO.builder()
                    .emailId("test@test.com1332")
                    .name("test")
                    .password("test")
                    .phoneNumber("12345678333")
                    .walletAddress("testtest1232")
                    .build();

            System.out.println(userService.registerUser(userDTO));

    }

    @Test
    public void getUser() {
        System.out.println(userService.getUser("test@test.com14"));
    }


    @Test
    public void updateUser() {
        UserDTO userDTO = UserDTO.builder()
                .emailId("test@test.com11113212321")
                .name("수정")
                .password("수정")
                .phoneNumber("12345678")
                .walletAddress("testtest")
                .build();

        System.out.println(userService.updateUser(userDTO));
    }


    @Test
    public void deleteUser(){
        UserDTO userDTO = UserDTO.builder()
                .emailId("test@test.com")
                .name("수정")
                .password("수정")
                .phoneNumber("12345678")
                .walletAddress("testtest")
                .build();

        System.out.println(userService.deleteUser(userDTO));
    }

    @Test
    public void validate(){
        UserDTO userDTO = UserDTO.builder()
                .emailId("test@test.com0123")
                .name("수정")
                .password("수정")
                .phoneNumber("123456780")
                .walletAddress("testtest")
                .build();

        userService.registerValidate(userDTO);
    }
}
