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
   private EntityManager em;

   @Autowired
   public  SpringConfig(EntityManager em){
       this.em = em;
   }

//  위의 코드로 대체함
//    private  DataSource dataSource;
//
//    @Autowired
//      public  SpringConfig(DataSource dataSource)
//    {
//        this.dataSource = dataSource;
//    }
    @Bean
    public MemberService memberService() {
        return  new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {

        //return  new MemoryMemberRepository();
       // return new JdbcMemberRepository(dataSource);
       // return  new JdbcTemplateMemberRepository(dataSource);
        return  new JpaMemberRepository(em);
    }
}


//helloController ->  memberService  -> memberRepository 식으로 묶여있어야한다.