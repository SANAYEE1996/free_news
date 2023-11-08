package toy.free_news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequestDto {

    private Long id;

    private int pageNumber;

    private String searchWord;
}
