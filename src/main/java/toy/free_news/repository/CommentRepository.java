package toy.free_news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import toy.free_news.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from comment c where c.news.id = :newsId")
    List<Comment> findCommentListByNewsId(Long newsId);
}
