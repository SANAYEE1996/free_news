package toy.free_news.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy.free_news.entity.Member;
import toy.free_news.jwt.TokenInfo;
import toy.free_news.repository.MemberRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("join test")
    void joinTest(){
        String email = "dudtkd0219@gmail.com";
        String name = "박영상";
        String password = "1234";

        memberService.saveMember(email,password,name);

        Member member = memberRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("not enrolled id"));
        TokenInfo tokenInfo = memberService.login(email, password);

        assertThat(member.getName()).isEqualTo(name);
        assertThat(tokenInfo.accessToken()).isNotBlank();
    }

    @Test
    @DisplayName("login test")
    void loginTest(){
        String email = "dudtkd0219@gmail.com";
        String password = "1234";

        TokenInfo tokenInfo = memberService.login(email, password);
        assertThat(tokenInfo.accessToken()).isNotBlank();
    }
}
