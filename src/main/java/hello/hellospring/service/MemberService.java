package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {

    // Alt+insert constructor 단축키
   // ctrl + alt + t  junit 생성
    private  MemberRepository memberRepository;

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원가입
    * */
    public Long join(Member member){
        //같은 이름이 있는 중복회원은 x
        // 꺼낼때는 get으로 꺼낼수도있다 Member member1 = result.get();
        // IllegalStateException 부정 또는 올바르지 않은 때에 메소드가 불려 간 것을 나타냅니다.
        // Shift+ctrl+Alt +T refactor this 단축키

        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
         return  member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                  throw  new IllegalStateException("이미 존재하는 회원");
          });
    }

    /* 전체화면 조회*/
    public List<Member> findMember(){
       return memberRepository.findAll();
    }

    public  Optional<Member> findOne(Long memberId) {
        return  memberRepository.findById(memberId);
    }

}
