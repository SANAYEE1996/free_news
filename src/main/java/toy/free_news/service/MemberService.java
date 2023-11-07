package toy.free_news.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import toy.free_news.entity.Member;
import toy.free_news.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(Member member){
        memberRepository.save(member);
    }

    public Member findMemberById(Long id){
        return memberRepository.findById(id).orElseThrow(()->new RuntimeException(id+" is not exists !"));
    }
}
