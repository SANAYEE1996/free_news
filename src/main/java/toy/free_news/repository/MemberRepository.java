package toy.free_news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.free_news.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
