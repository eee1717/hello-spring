package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

     Member save(Member member); // 저장기능
     Optional<Member> findById(Long id); //찾기기능
     Optional<Member> findByName(String name); //찾기기능
     List<Member> findAll(); //반환기능


     void clearStore();
}

