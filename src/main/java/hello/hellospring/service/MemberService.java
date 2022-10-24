package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
// JPA를 쓸려면 Transactional이 있어야지 쓸수있다. 이번의경우에는 회원가입할때 필요함으로 회원가입부분에 Transactional를 해도됨.
@Transactional
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


        // method의 호출시간을 알고싶을때
      long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member); //중복회원 검증
            memberRepository.save(member);
            return  member.getId();
        }finally {
         long finish =  System.currentTimeMillis();
         long timeMs =  finish- start;
         System.out.println("join =" +timeMs +"ms");
        }

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                  throw new IllegalStateException("이미 존재하는 회원");
          });
    }

    /* 전체화면 조회*/
    // 주의 동영상에서는 findMembers로되어있지만 나는 findMember로 만들어놓음
    public List<Member> findMember(){
        return memberRepository.findAll();
        // 단일로 AOP 하는방법 다중 AOP를할려면 class를만들어서 TimeTraceAop를 통해하는게 편함
//        long start = System.currentTimeMillis();
//          try {
//              return memberRepository.findAll();
//          }finally {
//              long finish =  System.currentTimeMillis();
//              long timeMs =  finish- start;
//              System.out.println("findMember =" +timeMs +"ms");
//          }
          }



    public  Optional<Member> findOne(Long memberId) {
        return  memberRepository.findById(memberId);
    }

}
