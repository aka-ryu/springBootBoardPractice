package com.practice.board.service;

import com.practice.board.dto.UserDTO;
import com.practice.board.entity.User;
import com.practice.board.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Log4j2
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    // 회원가입전 validate(pk, unique 중복체크)
    public void registerValidate(UserDTO userDTO) {
        Optional<User> optionalUser1 =
                userRepository.findById(userDTO.getEmailId());

        if(optionalUser1.isPresent()) {
            log.warn("이미 존재하는 이메일아이디");
            throw new RuntimeException("이미 존재하는 이메일아이디 입니다.");
        }

        Optional<User> optionalUser2 =
                userRepository.findByPhone(userDTO.getPhoneNumber());
        
        if(optionalUser2.isPresent()) {
            log.warn("이미 존재하는 전화번호");
            throw new RuntimeException("이미 존재하는 전화번호 입니다.");
        }
    }

    // 회원수정,탈퇴 이전에 유효한 회원 맞는지 검사
    // 탈퇴에도 굳이 필요한가?.. 는 싶긴한데...
    public void updateDeleteValidate(UserDTO userDTO) {
        Optional<User> optionalUser =
                userRepository.findById(userDTO.getEmailId());

        if(optionalUser.isEmpty()) {
            log.warn("존재하지 않는 회원 입니다.");
            throw new RuntimeException("존재하지 않는 회원입니다.");
        }
    }

    // 회원가입
    public String registerUser(UserDTO userDTO) {

        registerValidate(userDTO);
        User userEntity = UserDTO.toEntity(userDTO);
        userRepository.save(userEntity);

        return userDTO.getEmailId() + "의 회원가입이 완료되었습니다.";
    }


    // 회원조회
    public UserDTO getUser(String emailId){
        Optional<User> optionalUserEntity = userRepository.findById(emailId);
        User userEntity = optionalUserEntity.get();
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);

        return userDTO;
    }


    // 회원정보수정
    public String updateUser(UserDTO userDTO) {

        //회원이 존재하는지 체크
//        Optional<User> optionalUserEntity = userRepository.findById(userDTO.getEmailId());
//
//        if(optionalUserEntity.isPresent()) {
//            User userEntity = UserDTO.toEntity(userDTO);
//            userRepository.save(userEntity);
//
//            return "회원 정보 수정 완료";
//        } else {
//            return "존재하지 않는 회원";
//        }

        updateDeleteValidate(userDTO);

        User user = UserDTO.toEntity(userDTO);
        userRepository.save(user);

        return userDTO.getEmailId() + "의 정보가 수정되었습니다.";
    }


    // 회원삭제 (직접탈퇴 혹은 운영자제명)
    public String deleteUser(UserDTO userDTO) {
        //회원이 존재하는지 체크
//        Optional<User> optionalUserEntity = userRepository.findById(userDTO.getEmailId());
//
//        if(optionalUserEntity.isPresent()) {
//            User userEntity = UserDTO.toEntity(userDTO);
//            userRepository.delete(userEntity);
//
//            return "회원 탈퇴 완료";
//        } else {
//            return "존재하지 않는 회원";
//        }

        updateDeleteValidate(userDTO);

        User user = UserDTO.toEntity(userDTO);
        userRepository.delete(user);

        return userDTO.getEmailId() + "의 탈퇴(제명)이 완료되었습니다.";
    }
}
