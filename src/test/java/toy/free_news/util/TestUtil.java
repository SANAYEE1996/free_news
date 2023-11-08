package toy.free_news.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import toy.free_news.dto.LoginDto;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Component
public class TestUtil {

    @Value("${test.util.id}")
    private String id;

    @Value("${test.util.pw}")
    private String pw;

    @Autowired
    private WebApplicationContext webApplicationContext;

    public MockMvc loginWithJwtToken(MockMvc mockMvc, ObjectMapper objectMapper, RestDocumentationContextProvider restDocumentation) throws Exception{

        String result = mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new LoginDto(id,pw))))
                .andReturn().getResponse().getContentAsString();

        String body = new JSONObject(result).getString("body");
        String data = new JSONObject(body).getString("data");
        String token = new JSONObject(data).getString("accessToken");

        return MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(documentationConfiguration(restDocumentation))
                .defaultRequest(get("/").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .defaultRequest(post("/").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .build();
    }

    public MockMvc setRestDocumentation(RestDocumentationContextProvider restDocumentation){
        return MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(documentationConfiguration(restDocumentation))
                .build();
    }
}
