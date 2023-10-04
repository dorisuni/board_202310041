package com.icia.board.entity;

import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.CommentDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.stream.events.Comment;

@Entity
@Setter(AccessLevel.PRIVATE)
@Getter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String commentWriter;
    @Column(length = 200, nullable = false)
    private String commentContents;
    // boardId를 외래 키(Foreign Key)로 설정
    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    // DTO -> Entity 변환 메서드
    public static CommentEntity toSaveEntity(CommentDTO commentDTO){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        return commentEntity;
    }
    public static CommentEntity toUpdateEntity(CommentDTO commentDTO){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentDTO.getId());
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        return commentEntity;
    }

}
