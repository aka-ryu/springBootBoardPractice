package com.practice.board.controller;

import com.practice.board.dto.BoardDTO;
import com.practice.board.dto.ResponseDTO;
import com.practice.board.entity.BoardEntity;
import com.practice.board.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/board")
@Log4j2
public class BoardController {

    @Autowired
    private BoardService boardService;



//    @GetMapping("/list")
//    public ResponseEntity<?> boardList(){
//
//        List<BoardEntity> boardEntities = boardService.getBoardList();
//        log.info("1단계" + boardEntities);
//
//        List<BoardDTO> boardDTOS = boardEntities.stream().map(BoardDTO::new)
//                .collect(Collectors.toList());
//        log.info("2단계" + boardDTOS);
//
//        ResponseDTO<BoardDTO>  response =
//                ResponseDTO.<BoardDTO>builder().data(boardDTOS).build();
//        log.info("3단계" + response);
//
//        return ResponseEntity.ok().body(response);
//    }

    @GetMapping("/list")
    public ResponseEntity<?> getBoardListWithPaging(@RequestParam (defaultValue = "1") int page) {

        List<BoardDTO> boardDTOs = boardService.getBoardListWithPaging(page);
        ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(boardDTOs).build();

        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/register")
    public ResponseEntity<?> newBoard(@RequestBody BoardDTO boardDTO){

        try {
            boardService.BoardRegister(boardDTO);
        } catch (Exception e){
            String errMessage = e.getMessage();
            ResponseDTO<String> error = ResponseDTO.<String>builder().error(errMessage).build();

            return ResponseEntity.badRequest().body(error);
        }
            return ResponseEntity.ok().body("Board Register Success");

    }

}
