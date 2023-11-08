package toy.free_news.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;

    @NotNull(message = "newsId must not be null !")
    private Long newsId;

    @NotNull(message = "memberId must not be null !")
    private Long memberId;

    private String memberName;

    @NotBlank(message = "text must not be null !")
    private String text;

    private String registerDate;

    private String modifyDate;
}
