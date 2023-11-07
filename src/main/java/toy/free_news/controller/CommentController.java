package toy.free_news.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.free_news.dto.CommentDto;
import toy.free_news.service.CommentCombineService;
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

        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseDto.builder().message(e.getMessage()).build();
        }
        return ResponseDto.builder().build();
    }
}
