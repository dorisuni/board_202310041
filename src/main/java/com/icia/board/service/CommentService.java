package com.icia.board.service;

import com.icia.board.dto.CommentDTO;
import com.icia.board.entity.CommentEntity;
import com.icia.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Long save(CommentDTO commentDTO) {
        CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO);
        Long savedId = commentRepository.save(commentEntity).getId();
        return savedId;
    }

    public List<CommentDTO> findAll(Long boardId){
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardId(boardId);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        commentEntityList.forEach(entity -> {
            commentDTOList.add(CommentDTO.toDTO(entity));
        });
        return commentDTOList;
    }
}
