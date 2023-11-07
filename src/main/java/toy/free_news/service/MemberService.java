package toy.free_news.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.free_news.entity.Member;
import toy.free_news.jwt.JwtTokenProvider;
import toy.free_news.jwt.TokenInfo;
import toy.free_news.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    public void saveMember(String email, String password, String name){
        if(memberRepository.existsByEmail(email)){
            throw new RuntimeException("중복된 이메일 입니다.");
        }
        memberRepository.save(new Member(email, passwordEncoder.encode(password), name));
    }

    public Member findMemberById(Long id){
        return memberRepository.findById(id).orElseThrow(()->new RuntimeException(id+" is not exists !"));
    }

    @Transactional
    public TokenInfo login(String email, String password) throws RuntimeException{
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtTokenProvider.generateToken(authentication);
    }
}
