package toy.free_news.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    private Long id;

    @NotNull(message = "newsId must not be null !")
    private Long newsId;

    private int pageNumber;
}
