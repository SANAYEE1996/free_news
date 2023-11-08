package toy.free_news.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import toy.free_news.entity.News;
import toy.free_news.repository.NewsRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public Long saveNews(News news){
        return newsRepository.save(news).getId();
    }

    public void updateNews(String title, String text, String date, Long id){
        newsRepository.updateNewsInfoByNewsId(title, text, date, id);
    }

    public void deleteNews(Long id){
        newsRepository.deleteNewsInfoByNewsId(id);
    }

    public boolean newsIdExists(Long id){
        return newsRepository.existsById(id);
    }

    public News getNews(Long id){
        return newsRepository.findById(id).orElseThrow(()->new RuntimeException(id+" is not exist"));
    }

    public List<News> getNewsList(PageRequest pageRequest){
        return newsRepository.findAll(pageRequest).toList();
    }
}
