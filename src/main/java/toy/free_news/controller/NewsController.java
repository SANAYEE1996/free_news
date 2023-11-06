package toy.free_news.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import toy.free_news.dto.NewsDto;
import toy.free_news.service.CombineService;
import toy.free_news.util.ResponseDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final CombineService combineService;

    @PostMapping(value = "/save")
    public ResponseDto save(@RequestBody @Valid NewsDto newsDto){
        try {
            combineService.saveNews(newsDto);
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseDto.builder().message(e.getMessage()).build();
        }
        return ResponseDto.builder().build();
    }
}
