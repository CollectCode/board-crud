package spring_study.board_crud.api;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring_study.board_crud.domain.Board;
import spring_study.board_crud.dto.BoardDeleteDto;
import spring_study.board_crud.dto.BoardDto;
import spring_study.board_crud.service.BoardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

    private final BoardService boardService; 

    @GetMapping("/board.list")
    public WrapperClass board_list()    {
        List<Board> boardList = boardService.findBoards();
        List<BoardDto> boardDtoList = boardList.stream().map(b -> new BoardDto(b)).collect(Collectors.toList());
        return new WrapperClass(boardDtoList);
    }

    @GetMapping("/board-detail/{boardId}")
    public WrapperClass board_detail(@PathVariable("boardId") Long boardId){
        Board board = boardService.findOne(boardId);
        BoardDto boardDto = new BoardDto(board);
        return new WrapperClass(boardDto);
    }

    @PostMapping("/create-board")
    public ResponseEntity create_board(@RequestBody BoardDto boardDto)  {
        System.out.println("create_board/boardDto + " + boardDto);
        HttpHeaders headers = new HttpHeaders();
        HashMap<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.CREATED;
        try {
            Board board = new Board(
                boardDto.getTitle(),
                boardDto.getContent()
            );
            boardService.create(board);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            System.out.println("create_board/exception = " + e);
        }
        return new ResponseEntity(body, headers, status);
    }

    @PutMapping("/update-board")
    public ResponseEntity update_board(@RequestBody BoardDto boardDto)  {
        System.out.println("update_board/boardDto + " + boardDto.getId());
        HttpHeaders headers = new HttpHeaders();
        HashMap<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.CREATED;
        try {
            boardService.update(boardDto.getId(), boardDto.getTitle(), boardDto.getContent());
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            System.out.println("update_board/exception = " + e);
        }
        return new ResponseEntity(body, headers, status);
    }
    
    @DeleteMapping("/delete-board")
    public ResponseEntity delete_board(@RequestBody BoardDeleteDto boardDeleteDto)  {
        System.out.println("delete_board/boardDto + " + boardDeleteDto);
        HttpHeaders headers = new HttpHeaders();
        HashMap<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.NO_CONTENT;
        try {
            Board board = boardService.findOne(boardDeleteDto.getId());
            boardService.delete(board);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            System.out.println("delete_board/exception = " + e);
        }
        return new ResponseEntity(body, headers, status);
    }
}
