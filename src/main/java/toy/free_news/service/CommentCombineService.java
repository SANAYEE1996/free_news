package toy.free_news.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import toy.free_news.dto.CommentDto;
import toy.free_news.dto.CommentRequestDto;
import toy.free_news.entity.Comment;
import toy.free_news.entity.Member;
import toy.free_news.entity.News;
import toy.free_news.util.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentCombineService {

    private final CommentService commentService;

    private final NewsService newsService;

    private final MemberService memberService;

    private final Converter converter;

    public void saveComment(CommentDto commentDto){
        News news = newsService.getNews(commentDto.getNewsId());
        Member member = memberService.findMemberById(commentDto.getMemberId());
        String registerDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS"));
        commentService.save(new Comment(0L, news, member, member.getName(), commentDto.getText(), registerDate, null));
    }

    public void updateComment(CommentDto commentDto){
        if(isCommentIdNotValidate(commentDto.getId())){
            throw new RuntimeException("Id is Not validate");
        }
        String modifyDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS"));
        commentService.update(commentDto.getText(), modifyDate, commentDto.getId());
    }

    public void deleteComment(CommentRequestDto commentRequestDto){
        if(isCommentIdNotValidate(commentRequestDto.getId())){
            throw new RuntimeException("Id is Not validate");
        }
        commentService.delete(commentRequestDto.getId());
    }

    public List<CommentDto> getCommentList(CommentRequestDto commentRequestDto){
        News news = newsService.getNews(commentRequestDto.getNewsId());
        return converter.toCommentDtoList(commentService.findCommentList(news, PageRequest.of(commentRequestDto.getPageNumber(),10)));
    }

    private boolean isCommentIdNotValidate(Long id){
        if(id == null){
            log.error("id is null");
            return false;
        }
        return !commentService.isCommentIdExists(id);
    }

}
