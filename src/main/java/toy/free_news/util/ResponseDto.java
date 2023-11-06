package toy.free_news.util;

import lombok.Builder;

@Builder
public record ResponseDto(Integer code, String message, ResponseBody<?> body) {
}