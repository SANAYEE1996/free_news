package toy.free_news.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.free_news.dto.LoginDto;
import toy.free_news.dto.MemberDto;
import toy.free_news.service.MemberService;
import toy.free_news.util.ResponseBody;
import toy.free_news.util.ResponseDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/join")
    public ResponseDto save(@RequestBody @Valid MemberDto memberDto){
        try {
            memberService.saveMember(memberDto.getEmail(), memberDto.getPassword(), memberDto.getName());
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseDto.builder().message(e.getMessage()).build();
        }
        return ResponseDto.builder().message("join success").build();
    }

    @PostMapping(value = "/login")
    public ResponseDto login(@RequestBody @Valid LoginDto loginDto){
        try {
            return ResponseDto.builder().body(new ResponseBody<>(memberService.login(loginDto.getEmail(), loginDto.getPassword()))).message("login success").build();
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseDto.builder().message(e.getMessage()).build();
        }
    }
}
