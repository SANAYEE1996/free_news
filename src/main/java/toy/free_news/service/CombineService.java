package toy.free_news.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import toy.free_news.dto.NewsDto;
import toy.free_news.util.Converter;

@Slf4j
@Service
@RequiredArgsConstructor
public class CombineService {

    private final NewsService newsService;

    private final Converter converter;

    public void saveNews(NewsDto newsDto){

        newsService.saveNews(converter.toNewsEntity(newsDto));
    }
}
