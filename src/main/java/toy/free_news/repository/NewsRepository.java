package toy.free_news.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import toy.free_news.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Transactional
    @Modifying
    @Query("update news n set n.title = :title, n.text = :text, n.modifyDate = :modifyDate where n.id = :id")
    void updateNewsInfoByNewsId(String title, String text, String modifyDate, Long id);

    @Transactional
    @Modifying
    @Query("delete from news n where n.id = :id")
    void deleteNewsInfoByNewsId(Long id);
}
