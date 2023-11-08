package toy.free_news.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy.free_news.dto.NewsDto;
import toy.free_news.dto.NewsRequestDto;
import toy.free_news.repository.NewsRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class NewsServiceTest {

    @Autowired
    private NewsCombineService newsCombineService;

    @Autowired
    private NewsRepository newsRepository;

    @Test
    @DisplayName("save test")
    void saveTest(){

        NewsDto newsDto = new NewsDto(null, 1L, "속보3", "낚시3", "","");

        Long newsId = newsCombineService.saveNews(newsDto);

        NewsRequestDto requestDto = new NewsRequestDto(newsId, 0, "");
        NewsDto expectNewsDto = newsCombineService.getNewsDetail(requestDto);

        assertThat(newsDto.getMemberId()).isEqualTo(expectNewsDto.getMemberId());
        assertThat(newsDto.getTitle()).isEqualTo(expectNewsDto.getTitle());
        assertThat(newsDto.getText()).isEqualTo(expectNewsDto.getText());
    }

    @Test
    @DisplayName("update test")
    void updateTest(){

        NewsDto newsDto = new NewsDto(11L, 1L, "봄날이 간다 재발매", "뻥이야", "","");

        NewsRequestDto requestDto = new NewsRequestDto(11L, 0, "");

        newsCombineService.updateNews(newsDto);
        NewsDto expectNewsDto = newsCombineService.getNewsDetail(requestDto);

        assertThat(newsDto.getTitle()).isEqualTo(expectNewsDto.getTitle());
        assertThat(newsDto.getText()).isEqualTo(expectNewsDto.getText());
    }

    @Test
    @DisplayName("delete test")
    void deleteTest(){

        NewsDto newsDto = new NewsDto(null, 1L, "지워질 제목", "지워질 내용", "","");

        Long newsId = newsCombineService.saveNews(newsDto);

        NewsRequestDto requestDto = new NewsRequestDto(newsId, 0, "");

        newsCombineService.deleteNews(requestDto);

        assertThat(newsRepository.existsById(newsId)).isFalse();
    }

    @Test
    @DisplayName("detail test")
    void detailTest(){
        NewsRequestDto requestDto = new NewsRequestDto(11L, 0, "");

        NewsDto newsDto = newsCombineService.getNewsDetail(requestDto);

        assertThat(newsDto.getId()).isEqualTo(requestDto.getId());

        System.out.println("title : "+newsDto.getTitle());
        System.out.println("text : "+newsDto.getText());
        System.out.println("member : "+newsDto.getMemberId());
        System.out.println("register date : "+newsDto.getRegisterDate());
        System.out.println("modify date : "+newsDto.getModifyDate());
    }

    @Test
    @DisplayName("list test")
    void listTest(){
        NewsRequestDto requestDto = new NewsRequestDto(null, 0, "");

        List<NewsDto> newsDtoList = newsCombineService.getNewsList(requestDto);

        newsDtoList.stream()
                .map(e -> "title : "+e.getTitle()+ " // text : "+e.getText())
                    .forEach(System.out::println);
    }
}
