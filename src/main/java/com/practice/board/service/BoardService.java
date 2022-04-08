package com.practice.board.service;

import com.practice.board.dto.BoardDTO;
import com.practice.board.entity.BoardEntity;
import com.practice.board.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ModelMapper modelMapper;




    public BoardDTO getBoard(Long bno){
        Optional<BoardEntity> OptionalBoardEntity = boardRepository.findById(bno);
        BoardEntity boardEntity = OptionalBoardEntity.get();
        BoardDTO boardDTO = modelMapper.map(boardEntity, BoardDTO.class);

        return boardDTO;
    }


    public List<BoardEntity> getBoardList(){
       return boardRepository.findAll();
    }



    public List<BoardDTO> getBoardListWithPaging(int page){
        Sort sort = Sort.by("bno").descending();

        Pageable pageable = PageRequest.of(page, 10, sort);

        Page<BoardEntity> result = boardRepository.findAll(pageable);

        List<BoardDTO> boardDTOList = new ArrayList<>();

        result.get().forEach(board -> {
            BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
            boardDTOList.add(boardDTO);
        });

        return boardDTOList;
    }


    public void BoardRegister(BoardDTO boardDTO){
        BoardEntity boardEntity = BoardEntity.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .build();

        boardRepository.save(boardEntity);
    }

    @Transactional
    public void BoardUpdate(BoardDTO boardDTO){

        BoardEntity boardEntity = boardRepository.getById(boardDTO.getBno());
        boardEntity.changeBoard(boardDTO.getTitle(), boardDTO.getContent());

        boardRepository.save(boardEntity);
    }

    public void BoardRemove(Long bno){
        Optional<BoardEntity> OptionalBoardEntity = boardRepository.findById(bno);
        BoardEntity boardEntity = OptionalBoardEntity.get();

        boardRepository.delete(boardEntity);
    }

}
