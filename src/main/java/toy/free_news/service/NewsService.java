package toy.free_news.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import toy.free_news.entity.News;
import toy.free_news.repository.NewsRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public void saveNews(News news){
        newsRepository.save(news);
    }
}
