package com.practice.board.controller;

import com.practice.board.dto.ResponseDTO;
import com.practice.board.dto.UserDTO;
import com.practice.board.entity.User;
import com.practice.board.service.UserService;

import lombok.extern.log4j.Log4j2;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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


    // 조회
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable(value="id") String emailId){

        try {
            UserDTO userDTO = userService.getUser(emailId);

            List<UserDTO> list = new ArrayList<>();
            list.add(userDTO);

            ResponseDTO<UserDTO> result = ResponseDTO.<UserDTO>builder()
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


    // 가입
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){

        try {
            String service = userService.registerUser(userDTO);

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
            return ResponseEntity.ok().body(result);
        }
    }


    // 정보수정
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){

        try {
            String service = userService.updateUser(userDTO);

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

    // 탈퇴,강퇴
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody UserDTO userDTO){

        try {
            String service = userService.deleteUser(userDTO);

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

    // 로그인
    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {

        try {
            String service = userService.signin(userDTO);

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
            return ResponseEntity.ok().body(result);
        }
    }
}
