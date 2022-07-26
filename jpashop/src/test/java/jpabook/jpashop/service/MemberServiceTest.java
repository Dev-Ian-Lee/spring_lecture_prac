package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    // 회원가입 테스트
    @Test
    public void memberJoin() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");
        
        //when
        Long joinedMemberId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findMember(joinedMemberId));
    
    }

    // 중복회원 예외 테스트
    @Test
    public void duplicateMemberException() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member);

//        try {
//            memberService.join(member2);        // 예외 발생해야 함
//        } catch (IllegalStateException e) {
//            return;                             // 예외 발생 시 테스트 성공
//        }

        //then
        // 테스트 코드가 위에서 끝나지 않고 여기까지 오면 테스트 실패
//        fail("예외 발생");

        // JUnit5는 test에 expected 설정을 할 수 없어 이와 같이 대체
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }

}