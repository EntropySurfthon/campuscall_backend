package surfthon.campus_call.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;
    private int count;

    // 기본 생성자
    public Keyword() {}

    // 생성자
    public Keyword(String word) {
        this.word = word;
        this.count = 1; // 단어가 처음 저장될 때 count를 1로 설정
    }

    public void incrementCount() {
        this.count++;
    }
}
