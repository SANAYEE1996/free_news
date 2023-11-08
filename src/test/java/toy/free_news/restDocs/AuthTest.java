package toy.free_news.restDocs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import toy.free_news.dto.LoginDto;
import toy.free_news.dto.MemberDto;
import toy.free_news.repository.MemberRepository;
import toy.free_news.util.TestUtil;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AuthTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestUtil testUtil;

    @Autowired
    private MemberRepository memberRepository;

    @RegisterExtension
    final RestDocumentationExtension restDocumentation = new RestDocumentationExtension("build/generated-snippets");

    @BeforeEach
    void initial(RestDocumentationContextProvider restDocumentation){
        mockMvc = testUtil.setRestDocumentation(restDocumentation);
    }

    @DisplayName("회원가입 api 테스트")
    @Test
    void saveTest() throws Exception {

        String dummyEmail = "test01@gmail.com";

        String content = objectMapper.writeValueAsString(new MemberDto(dummyEmail,"1234","test001"));

        mockMvc.perform(post("/member/join").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("member/join",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        memberRepository.deleteMemberInfoByEmail(dummyEmail);
    }

    @DisplayName("로그인 api 테스트")
    @Test
    void LoginTest() throws Exception {

        String content = objectMapper.writeValueAsString(new LoginDto("dudtkd0219@gmail.com","1234"));

        mockMvc.perform(post("/member/login").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document("member/login",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
