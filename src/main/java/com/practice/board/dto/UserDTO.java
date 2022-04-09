package com.practice.board.dto;

import com.practice.board.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String emailId;
    private String password;
    private String name;
    private String phoneNumber;
    private String walletAddress;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public UserDTO (User user) {
        this.emailId = user.getEmailId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.walletAddress = user.getWalletAddress();
        this.createDate = user.getCreateDate();
        this.lastModifiedDate = user.getLastModifiedDate();
    }

    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .emailId(userDTO.getEmailId())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .phoneNumber(userDTO.getPhoneNumber())
                .walletAddress(userDTO.getWalletAddress())
                .build();
    }
}
