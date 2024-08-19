package surfthon.campus_call.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import surfthon.campus_call.domain.Keyword;
import surfthon.campus_call.repository.KeywordRepository;

import java.util.List;

@Service
@Slf4j
public class KeywordService {

    private final KeywordRepository keywordRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    @Transactional
    public void saveKeywords(String responseText) {
        // 쉼표로 구분된 단어들을 분리
        String[] keywords = responseText.split(",");

        // 각 키워드를 처리
        for (String word : keywords) {
            word = word.trim(); // 공백 제거
            saveOrUpdateKeyword(word);
        }
    }

    private void saveOrUpdateKeyword(String word) {
        // 데이터베이스에서 단어를 검색
        Keyword keyword = entityManager
                .createQuery("SELECT k FROM Keyword k WHERE k.word = :word", Keyword.class)
                .setParameter("word", word)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (keyword != null) {
            // 이미 존재하는 경우 count 증가
            keyword.incrementCount();
            entityManager.merge(keyword);
        } else {
            // 존재하지 않는 경우 새로 저장
            Keyword newKeyword = new Keyword(word);
            entityManager.persist(newKeyword);
        }
    }

    public List<Keyword> getTop10Keywords() {
        List<Keyword> keywords = entityManager.createQuery(
                        "SELECT k FROM Keyword k ORDER BY k.count DESC", Keyword.class)
                .setMaxResults(10)
                .getResultList();

        log.info("Top 10 Keywords: {}", keywords);
        return keywords;
    }
}
