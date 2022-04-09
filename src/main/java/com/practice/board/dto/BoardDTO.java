package com.practice.board.dto;

import com.practice.board.entity.Board;
import lombok.*;


import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public BoardDTO(Board boardEntity) {
        this.bno = boardEntity.getBno();
        this.title = boardEntity.getTitle();
        this.content = boardEntity.getContent();
        this.writer = boardEntity.getWriter();
        this.createDate = boardEntity.getCreateDate();
        this.lastModifiedDate = boardEntity.getLastModifiedDate();
    }

    public static Board toEntity(BoardDTO boardDTO) {
            return Board.builder()
                    .title(boardDTO.getTitle())
                    .content(boardDTO.getContent())
                    .writer(boardDTO.getWriter())

                    .build();
    }
}
