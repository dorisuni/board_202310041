package com.icia.board.service;

import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.MemberDTO;
import com.icia.board.entity.BoardEntity;
import com.icia.board.entity.MemberEntity;
import com.icia.board.repository.BoardRepository;
import com.icia.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;


    public Long save(BoardDTO boardDTO) {
        System.out.println("boardDTO = " + boardDTO);
        // DTO -> Entity 변환을 위한 메서드 호출
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        Long savedId = boardRepository.save(boardEntity).getId();
        return savedId;
    }


//    public List<BoardDTO> pagingList(int page) {
//        int pageLimit = 10; // 한페이지당 보여줄 글 갯수
//        int pagingStart = (page - 1) * pageLimit; // 요청한 페이지에 보여줄 첫번째 게시글의 순서
//        Map<String, Integer> pagingParams = new HashMap<>();
//        pagingParams.put("start", pagingStart);
//        pagingParams.put("limit", pageLimit);
//        return boardRepository.pagingList(pagingParams);
//    }

    public List<BoardDTO> findAll1() {
        List<BoardEntity> boardEntityList = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<BoardDTO> boardDTOList = new ArrayList<>();
        boardEntityList.forEach(entity -> {
            boardDTOList.add(BoardDTO.toDTO(entity));
        });
        return boardDTOList;
    }

    public BoardDTO findById(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(() -> new NoSuchElementException());

        return BoardDTO.toDTO(boardEntity);

    }

    /* Views Counting
    * 서비스 클래스 메서드에서 @Transactional 붙이는 경우
    * 1. jpql로 작성한 메서드 호출할 때
    * 2. 부모엔티티에서 자식엔티티를 바로 호출할 때
    *
    * */
    @Transactional
    public int incrementBoardHitsById(Long id) {
        return boardRepository.incrementBoardHitsById(id);
    }

}
