package toy.free_news.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import toy.free_news.dto.NewsDto;
import toy.free_news.dto.NewsRequestDto;
import toy.free_news.service.NewsCombineService;
import toy.free_news.util.ResponseBody;
import toy.free_news.util.ResponseDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsCombineService newsCombineService;

    @PostMapping(value = "/save")
    public ResponseDto save(@RequestBody @Valid NewsDto newsDto){
        try {
            newsCombineService.saveNews(newsDto);
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseDto.builder().message(e.getMessage()).build();
        }
        return ResponseDto.builder().message("news save success").build();
    }

    @PostMapping(value = "/update")
    public ResponseDto update(@RequestBody @Valid NewsDto newsDto){
        try {
            newsCombineService.updateNews(newsDto);
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseDto.builder().message(e.getMessage()).build();
        }
        return ResponseDto.builder().message("news update success").build();
    }

    @PostMapping(value = "/delete")
    public ResponseDto delete(@RequestBody @Valid NewsRequestDto newsRequestDto){
        try {
            newsCombineService.deleteNews(newsRequestDto);
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseDto.builder().message(e.getMessage()).build();
        }
        return ResponseDto.builder().message("news delete success").build();
    }

    @PostMapping(value = "/detail")
    public ResponseDto detail(@RequestBody @Valid NewsRequestDto newsRequestDto){
        try {
            return ResponseDto.builder()
                            .body(new ResponseBody<>(newsCombineService.getNewsDetail(newsRequestDto)))
                            .message("news detail get success")
                            .build();
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseDto.builder().message(e.getMessage()).build();
        }
    }

    @PostMapping(value = "/list")
    public ResponseDto list(@RequestBody @Valid NewsRequestDto newsRequestDto){
        try {
            return ResponseDto.builder()
                    .body(new ResponseBody<>(newsCombineService.getNewsList(newsRequestDto)))
                    .message("news detail get success")
                    .build();
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseDto.builder().message(e.getMessage()).build();
        }
    }
}
