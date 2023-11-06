package toy.free_news.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {

    private Long id;

    @NotNull(message = "memberId must not Null")
    private Long memberId;

    @NotBlank(message = "memberId must not Null")
    private String title;

    @NotBlank(message = "memberId must not Null")
    private String text;

    private String registerDate;

    private String modifyDate;
}
