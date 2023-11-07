package toy.free_news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.free_news.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
}
