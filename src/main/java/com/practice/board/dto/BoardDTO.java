package com.practice.board.dto;

import com.practice.board.entity.BoardEntity;
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

    public BoardDTO(BoardEntity boardEntity) {
        this.bno = boardEntity.getBno();
        this.title = boardEntity.getTitle();
        this.content = boardEntity.getContent();
        this.writer = boardEntity.getWriter();
        this.createDate = boardEntity.getCreateDate();
        this.lastModifiedDate = boardEntity.getLastModifiedDate();
    }

    public static BoardEntity toEntity(BoardDTO boardDTO) {
            return BoardEntity.builder()
                    .title(boardDTO.getTitle())
                    .content(boardDTO.getContent())
                    .writer(boardDTO.getWriter())

                    .build();
    }
}
