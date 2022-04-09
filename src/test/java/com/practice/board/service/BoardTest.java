package com.practice.board.service;

import com.practice.board.dto.BoardDTO;
import com.practice.board.entity.Board;
import com.practice.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class BoardTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void getBoard(){
        System.out.println(boardService.getBoard(2l));
    }

    @Test
    public void getBoardList(){

        System.out.println(boardService.getBoardList());
    }


    @Test
    public void BoardRegisterTest(){

        for(int i = 1; i < 100; i++) {
            BoardDTO boardDTO = BoardDTO.builder()
                    .title("테스트제목" + i)
                    .content("테스트내용" + i)
                    .writer("테스터" + i)
                    .build();

            boardService.BoardRegister(boardDTO);
        }

        System.out.println(boardService.getBoardList());
    }


    @Test
    public void BoardUpdateTest() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(3L)
                .title("수정제목")
                .content("수정내용")
                .build();

        boardService.BoardUpdate(boardDTO);

        System.out.println(boardService.getBoardList());
    }

    @Test
    public void BoardDeleteTest() {
        boardService.BoardRemove(2L);
        System.out.println(boardService.getBoardList());
    }

    @Test
    public void getBoardListWithPaging() {
        Sort sort = Sort.by("bno").descending();
        Pageable pageable = PageRequest.of(2,20, sort);
        Page<Board> result = boardRepository.findAll(pageable);

        System.out.println("---------------------");

        result.get().forEach(board -> {
            System.out.println(board);
        });
    }


    @Test
    public void getBoardListWithPaging2() {
        List<BoardDTO> boardDTOList = boardService.getBoardListWithPaging(4);

        for(BoardDTO board : boardDTOList){
            System.out.println(board);
        }
    }

}
