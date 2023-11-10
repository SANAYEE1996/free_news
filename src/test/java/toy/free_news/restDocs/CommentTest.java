package toy.free_news.restDocs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import toy.free_news.dto.CommentDto;
import toy.free_news.dto.CommentRequestDto;
import toy.free_news.util.TestUtil;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class CommentTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestUtil testUtil;

    @RegisterExtension
    final RestDocumentationExtension restDocumentation = new RestDocumentationExtension("build/generated-snippets");

    @BeforeEach
    void initial(RestDocumentationContextProvider restDocumentation) throws Exception {
        mockMvc = testUtil.loginWithJwtToken(mockMvc,objectMapper,restDocumentation);
    }

    @DisplayName("comment 저장 테스트")
    @Test
    void saveTest() throws Exception {

        CommentDto commentDto = new CommentDto(null, 1L, 1L, "박영상", "저기요", "", "");

        String content = objectMapper.writeValueAsString(commentDto);

        mockMvc.perform(post("/comment/save").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("comment/save",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @DisplayName("comment 수정 테스트")
    @Test
    void updateTest() throws Exception {

        CommentDto commentDto = new CommentDto(5L, 1L, 1L, "박영상", "수정 !", "", "");

        String content = objectMapper.writeValueAsString(commentDto);

        mockMvc.perform(post("/comment/update").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("comment/update",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @DisplayName("comment 삭제 테스트")
    @Test
    void deleteTest() throws Exception {

        CommentRequestDto commentRequestDto = new CommentRequestDto(7L, 0L, 0);

        String content = objectMapper.writeValueAsString(commentRequestDto);

        mockMvc.perform(post("/comment/delete").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("comment/delete",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @DisplayName("comment 리스트 조회 테스트")
    @Test
    void listTest() throws Exception {

        CommentRequestDto commentRequestDto = new CommentRequestDto(0L, 1L, 0);

        String content = objectMapper.writeValueAsString(commentRequestDto);

        mockMvc.perform(post("/comment/list").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("comment/list",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
