package com.practice.board.dto;

import com.practice.board.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String userid;
    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private String walletAddress;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public UserDTO (UserEntity userEntity) {
        this.userid = userEntity.getUserid();
        this.name = userEntity.getName();
        this.password = userEntity.getPassword();
        this.phoneNumber = userEntity.getPhoneNumber();
        this.email = userEntity.getEmail();
        this.walletAddress = userEntity.getWalletAddress();
        this.createDate = userEntity.getCreateDate();
        this.lastModifiedDate = userEntity.getLastModifiedDate();
    }

    public static UserEntity toEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .phoneNumber(userDTO.getPhoneNumber())
                .email(userDTO.getEmail())
                .walletAddress(userDTO.getWalletAddress())
                .build();
    }
}
