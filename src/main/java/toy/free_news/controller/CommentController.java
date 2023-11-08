package toy.free_news.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.free_news.dto.CommentDto;
import toy.free_news.dto.CommentRequestDto;
import toy.free_news.service.CommentCombineService;
import toy.free_news.util.ResponseBody;
import toy.free_news.util.ResponseDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentCombineService commentCombineService;

    @PostMapping(value = "/save")
    public ResponseDto save(@RequestBody @Valid CommentDto commentDto){
        try {
            commentCombineService.saveComment(commentDto);
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseDto.builder().message(e.getMessage()).build();
        }
        return ResponseDto.builder().message("comment save success").build();
    }

    @PostMapping(value = "/update")
    public ResponseDto update(@RequestBody @Valid CommentDto commentDto){
        try {
            commentCombineService.updateComment(commentDto);
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseDto.builder().message(e.getMessage()).build();
        }
        return ResponseDto.builder().message("comment update success").build();
    }

    @PostMapping(value = "/delete")
    public ResponseDto delete(@RequestBody @Valid CommentRequestDto commentRequestDto){
        try {
            commentCombineService.deleteComment(commentRequestDto);
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseDto.builder().message(e.getMessage()).build();
        }
        return ResponseDto.builder().message("comment delete success").build();
    }

    @PostMapping(value = "/list")
    public ResponseDto list(@RequestBody @Valid CommentRequestDto commentRequestDto){
        try {
            return ResponseDto.builder().body(new ResponseBody<>(commentCombineService.getCommentList(commentRequestDto))).message("list get success").build();
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseDto.builder().message(e.getMessage()).build();
        }
    }
}
