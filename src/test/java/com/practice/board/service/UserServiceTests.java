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

        for (int i=0; i<100; i++) {
            UserDTO userDTO = UserDTO.builder()
                    .emailId("test@test.com"+i)
                    .name("test"+i)
                    .password("test"+i)
                    .phoneNumber("123456783"+i)
                    .walletAddress("testtest"+i)
                    .build();

            System.out.println(userService.registerUser(userDTO));
        }

    }

    @Test
    public void getUser() {
        System.out.println(userService.getUser("test@test.com14"));
    }


    @Test
    public void updateUser() {
        UserDTO userDTO = UserDTO.builder()
                .emailId("test@test.com2")
                .name("수정1")
                .password("수정1")
                .phoneNumber("1234567833133")
                .walletAddress("testtest133")
                .build();

        System.out.println(userService.updateUser(userDTO));
    }


    @Test
    public void deleteUser(){
        UserDTO userDTO = UserDTO.builder()
                .emailId("test@test.com1")
                .name("수정")
                .password("수정")
                .phoneNumber("12345678")
                .walletAddress("testtest")
                .build();

        System.out.println(userService.deleteUser(userDTO));
    }

    @Test
    public void registerValidate(){
        UserDTO userDTO = UserDTO.builder()
                .emailId("test@test.com0123")
                .name("수정")
                .password("수정")
                .phoneNumber("12345678")
                .walletAddress("testtest")
                .build();

        userService.registerValidate(userDTO);
    }

}
