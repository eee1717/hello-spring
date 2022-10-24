package hello.hellospring;


import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class SpringConfig {
 //  @PersistenceContext 해도되고 않해도되고



//   private EntityManager em;
//
//   @Autowired
//   public  SpringConfig(EntityManager em){
//       this.em = em;
//   }




//  위의 코드로 대체함
//    private  DataSource dataSource;
//
//    @Autowired
//      public  SpringConfig(DataSource dataSource)
//    {
//        this.dataSource = dataSource;
//    }



   private  final  MemberRepository memberRepository;

    @Autowired  //생략가능
    public SpringConfig(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    //23 springdatajpa 에서  MemberService 관련을 변경시켰음 오류날시 확인해볼것. 이전까진 정상작동. memberRepository() 에서 ()를제거함.
    @Bean
    public MemberService memberService() {
        return  new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//
//        //return  new MemoryMemberRepository();
//       // return new JdbcMemberRepository(dataSource);
//       // return  new JdbcTemplateMemberRepository(dataSource);
//       // return  new JpaMemberRepository(em);
//
//    }


    //이렇게해서 AOP 적용해도되고 아니면 TimeTraceAop class에 component로 해서 넣어도된다.
//    @Bean
//    public  TimeTraceAop timeTraceAop()
//    {
//        return  new TimeTraceAop();
//    }




}

//helloController ->  memberService  -> memberRepository 식으로 묶여있어야한다.



