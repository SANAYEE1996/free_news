package toy.free_news.dto;

import lombok.Getter;

@Getter
public class NewsRequestDto {

    private Long id;

    private int pageNumber;

    private String searchWord;
}
