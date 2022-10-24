package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Entity가 있는곳의내용을 적는다 Member 와 Member의 id의 type 을적는다.
//SpringDataJpa가 자동으로 구현체를만들어서 등록해준다. 그걸 가져와야한다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // jpa에서 이렇게 query를 사용한다. 적으면
    // JPQL  select m from Member  m where m.name =?  로 적용됨
    @Override
    Optional<Member> findByName(String name);

}
