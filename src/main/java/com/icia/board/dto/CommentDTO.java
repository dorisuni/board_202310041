package com.icia.board.dto;

import com.icia.board.entity.BoardEntity;
import com.icia.board.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private long boardId;

    //Entity -> DTO
    public static CommentDTO toDTO(CommentEntity commentEntity){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setBoardId(commentDTO.getBoardId());
        return commentDTO;
    }
}
