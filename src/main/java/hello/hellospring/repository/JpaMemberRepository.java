package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements  MemberRepository{

     private final EntityManager em;

     public  JpaMemberRepository(EntityManager em){
         this.em = em;
     }

   //persist() 효과 영구저장
    @Override
    public Member save(Member member) {
         em.persist(member);
         return  member;
    }

    @Override  //id 조회
    public Optional<Member> findById(Long id) {
      Member member =  em.find(Member.class, id);
       return  Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
     List<Member> result =  em.createQuery("select m from Member m where m.name = :name", Member.class)
               .setParameter("name",name)
               .getResultList();

        return  result.stream().findAny();
    }

    // ctrl + alt + n : inline 한줄로 만들기 단축키
    // jpql 객체를대상으로 Query를 날리는것   //보통의sql문은 table을 대상으로 Query를날린다.
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }




    // clearStore()가 원래는없다 근데 왜없는지모르겠다. 없으면 작동안됨.
    // clearStore()는 데이터 중복의 문제가 발생할때 테스트할때 쓰이는것이다.
//    @Override
//    public void clearStore() {
//
//    }
}
