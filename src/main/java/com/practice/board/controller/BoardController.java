package com.practice.board.controller;

import com.practice.board.dto.BoardDTO;
import com.practice.board.dto.ResponseDTO;
import com.practice.board.entity.BoardEntity;
import com.practice.board.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/board")
@Log4j2
public class BoardController {

    @Autowired
    private BoardService boardService;



    @GetMapping("/list")
    public ResponseEntity<?> boardList(){

        List<BoardEntity> boardEntities = boardService.getBoardList();
        log.info("1단계" + boardEntities);

        List<BoardDTO> boardDTOS = boardEntities.stream().map(BoardDTO::new)
                .collect(Collectors.toList());
        log.info("2단계" + boardDTOS);

        ResponseDTO<BoardDTO>  response =
                ResponseDTO.<BoardDTO>builder().data(boardDTOS).build();
        log.info("3단계" + response);

        return ResponseEntity.ok().body(response);

    }

}
