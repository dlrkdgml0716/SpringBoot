package HelloWorld.HelloWorld_Spring.service;

import HelloWorld.HelloWorld_Spring.domain.Member;
import HelloWorld.HelloWorld_Spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired  MemberService memberService;
    @Autowired  MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        //given
        //when
        Member member = new Member();
        member.setName("user2");

        long saveId = getJoin(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    private long getJoin(Member member) {
        return memberService.join(member);
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("user2");

        Member member2 = new Member();
        member2.setName("user2");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }
}