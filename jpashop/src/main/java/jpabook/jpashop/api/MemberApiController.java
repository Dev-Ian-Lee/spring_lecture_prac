package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    // DTO 사용 없이 엔티티만 사용 -> 문제 많음
    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findAllMembers();
    }

    // DTO 사용
    @GetMapping("/api/v2/members")
    public Result membersV2() {
        // Service 사용해 멤버 목록 가져옴
        List<Member> foundMembers = memberService.findAllMembers();

        // 가져온 멤버 객체를 DTO로 변환
        List<MemberDto> collect = foundMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        // Result 클래스로 감싸서 반환(감싸지 않으면 json이 배열 타입으로 반환되기 때문에 유연성 감소)
        return new Result(collect.size(), collect);
    }

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @PutMapping("api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getName());
        Member foundMember = memberService.findMember(id);

        return new UpdateMemberResponse(id, foundMember.getName());
    }

    @Data
    static class CreateMemberRequest {
        private String name;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }


}
