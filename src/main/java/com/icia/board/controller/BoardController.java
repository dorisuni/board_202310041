package com.icia.board.controller;

import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.MemberDTO;
import com.icia.board.service.BoardService;
import com.icia.board.service.CommentService;
import com.icia.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;
    @GetMapping("/save")
    public String save() {
        return "/boardPages/boardSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        System.out.println("BoardController.save");
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "redirect:/board";
    }

//    @GetMapping("/")
//    public String findAll(@RequestParam(value = "page",required = false,defaultValue = "1") int page, Model model{
//        List<BoardDTO> boardDTOList = boardService.pagingList(page);
//    }

    @GetMapping("/list")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll1();
        System.out.println("boardDTOList = " + boardDTOList);
        model.addAttribute("boardList", boardDTOList);
        return "boardPages/boardList";
    }

    @GetMapping
    public String findAll1(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll1();
        System.out.println("boardDTOList = " + boardDTOList);
        model.addAttribute("boardList", boardDTOList);
        return "boardPages/boardList";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {

        try {
            BoardDTO boardDTO = boardService.findById(id);
            boardService.incrementBoardHitsById(id); // boardHits ++
            model.addAttribute("board", boardDTO);
            return "/boardPages/boardDetail";
        } catch (NoSuchElementException e) {
            return "/boardPages/NotFound";
        }
    }



}
