package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUserName("memberA");

        //when
        Long savedId = memberRepository.save(member);
        Member foundMember = memberRepository.find(savedId);

        //then
        Assertions.assertThat(foundMember.getId()).isEqualTo(savedId);
        Assertions.assertThat(foundMember.getUserName()).isEqualTo(member.getUserName());
        Assertions.assertThat(foundMember).isEqualTo(member);
    }
}