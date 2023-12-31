package toy.free_news.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import toy.free_news.dto.NewsDto;
import toy.free_news.dto.NewsRequestDto;
import toy.free_news.util.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsCombineService {

    private final NewsService newsService;

    private final MemberService memberService;

    private final Converter converter;

    public Long saveNews(NewsDto newsDto){
        return newsService.saveNews(converter.toNewsEntity(memberService.findMemberById(newsDto.getMemberId()), newsDto));
    }

    public void updateNews(NewsDto newsDto){
        if(isNewsIdNotValidate(newsDto.getId())){
            throw new RuntimeException("news id is not validate");
        }
        newsService.updateNews(newsDto.getTitle(), newsDto.getText(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS")), newsDto.getId());
    }

    public void deleteNews(NewsRequestDto newsRequestDto){
        if(isNewsIdNotValidate(newsRequestDto.getId())){
            throw new RuntimeException("news id is not validate");
        }
        newsService.deleteNews(newsRequestDto.getId());
    }

    public NewsDto getNewsDetail(NewsRequestDto newsRequestDto){
        if(isNewsIdNotValidate(newsRequestDto.getId())){
            throw new RuntimeException("news id is not validate");
        }
        return converter.toNewsDto(newsService.getNews(newsRequestDto.getId()));
    }

    public List<NewsDto> getNewsList(NewsRequestDto newsRequestDto){
        return converter.toNewsDtoList(newsService.getNewsList(PageRequest.of(newsRequestDto.getPageNumber(),10)));
    }

    private boolean isNewsIdNotValidate(Long id){
        if(id == null) {
            log.error("id is null");
            return false;
        }
        return !newsService.newsIdExists(id);
    }
}
