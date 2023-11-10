package toy.free_news.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import toy.free_news.entity.Comment;
import toy.free_news.entity.News;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Transactional
    @Modifying
    @Query("update comment c set c.text = :text, c.modifyDate = :modifyDate where c.id = :id")
    void updateCommentInfo(String text, String modifyDate, Long id);

    @Transactional
    @Modifying
    @Query("delete from comment c where c.id = :id")
    void deleteCommentInfoById(Long id);

    Page<Comment> findByNewsOrderByRegisterDate(News news, Pageable pageable);
}
