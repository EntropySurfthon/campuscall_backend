package surfthon.campus_call.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import surfthon.campus_call.domain.Keyword;

import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Optional<Keyword> findByWord(String word);
}
