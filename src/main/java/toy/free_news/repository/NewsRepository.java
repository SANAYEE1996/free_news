package toy.free_news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.free_news.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
