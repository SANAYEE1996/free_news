package toy.free_news.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import toy.free_news.entity.Comment;
import toy.free_news.entity.News;
import toy.free_news.repository.CommentRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void save(Comment comment){
        commentRepository.save(comment);
    }

    public void update(String text, String modifyDate, Long id){
        commentRepository.updateCommentInfo(text, modifyDate, id);
    }

    public void delete(Long id){
        commentRepository.deleteCommentInfoById(id);
    }

    public List<Comment> findCommentList(News news, PageRequest pageRequest){
        return commentRepository.findByNewsOrderByRegisterDate(news, pageRequest).toList();
    }

    public boolean isCommentIdExists(Long id){
        return commentRepository.existsById(id);
    }
}
