package toy.free_news.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy.free_news.dto.CommentDto;
import toy.free_news.dto.CommentRequestDto;
import toy.free_news.entity.Comment;
import toy.free_news.repository.CommentRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentCombineService commentCombineService;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("save test")
    void saveTest(){
        CommentDto commentDto = new CommentDto(null, 1L, 1L, "박영상", "게임 사주세요", "", "");
        commentCombineService.saveComment(commentDto);
    }

    @Test
    @DisplayName("update test")
    void updateTest(){
        CommentDto commentDto = new CommentDto(1L, 1L, 1L, "박영상", "악플 !", "", "");
        commentCombineService.updateComment(commentDto);
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(()-> new RuntimeException("fail"));
        assertThat(commentDto.getMemberId()).isEqualTo(comment.getMember().getId());
        assertThat(commentDto.getText()).isEqualTo(comment.getText());
    }

    @Test
    @DisplayName("delete test")
    void deleteTest(){
        CommentRequestDto commentRequestDto = new CommentRequestDto(4L, 0L, 0);
        commentCombineService.deleteComment(commentRequestDto);
        assertThat(commentRepository.existsById(commentRequestDto.getId())).isFalse();
    }

    @Test
    @DisplayName("list test")
    void listTest(){
        CommentRequestDto commentRequestDto = new CommentRequestDto(0L, 1L, 0);
        List<CommentDto> commentDtoList = commentCombineService.getCommentList(commentRequestDto);
        commentDtoList.stream()
                .map(e->"text : " +e.getText()+ " name : "+e.getMemberName())
                .forEach(System.out::println);
    }
}
