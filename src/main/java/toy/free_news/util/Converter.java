package toy.free_news.util;

import org.springframework.stereotype.Component;
import toy.free_news.dto.NewsDto;
import toy.free_news.entity.Member;
import toy.free_news.entity.News;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {

    public News toNewsEntity(Member member, NewsDto newsDto){
        return new News(member, newsDto.getTitle(), newsDto.getText(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS")));
    }

    public List<NewsDto> toNewsDtoList(List<News> newsList){
        return newsList.stream().map(this::toNewsDto).collect(Collectors.toList());
    }

    public NewsDto toNewsDto(News news){
        return new NewsDto(news.getId(), news.getMember().getId(), news.getTitle(), news.getText(), news.getRegisterDate(), news.getModifyDate());
    }
}
