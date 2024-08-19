package surfthon.campus_call.loader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import surfthon.campus_call.domain.Keyword;
import surfthon.campus_call.domain.Department;
import surfthon.campus_call.repository.KeywordRepository;
import surfthon.campus_call.repository.DepartmentRepository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class loader implements CommandLineRunner {

    private final KeywordRepository keywordRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public loader(KeywordRepository keywordRepository, DepartmentRepository departmentRepository) {
        this.keywordRepository = keywordRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadDepartmentsAndKeywordsFromCSV();
    }

    private void loadDepartmentsAndKeywordsFromCSV() {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/keywords.csv")))) {
            String[] values;
            csvReader.readNext(); // Skip header row

            while ((values = csvReader.readNext()) != null) {
                String id = values[0];
                String departmentName = values[1];
                String link = values[2];
                String duty = values[3];
                String pno = values[4];
                String keywordsString = values[5];

                // 키워드 리스트를 쉼표로 구분된 문자열로 변환
                String keywordString = String.join(", ", Arrays.asList(keywordsString.split(",\\s*")));

                // Department 엔티티 저장
                Department department = new Department();
                department.setId(Long.parseLong(id)); // id를 Long으로 변환하여 설정
                department.setName(departmentName);
                department.setLink(link);
                department.setDuty(duty);
                department.setPno(pno);
                department.setKeyword(keywordString); // 문자열로 저장

                // Department 저장
                departmentRepository.save(department);

                // Keyword 엔티티 저장
                List<String> keywords = Arrays.asList(keywordsString.split(",\\s*"));
                for (String keywordWord : keywords) {
                    Optional<Keyword> existingKeyword = keywordRepository.findByWord(keywordWord);
                    if (existingKeyword.isPresent()) {
                        Keyword keyword = existingKeyword.get();
                        keyword.incrementCount(); // 기존의 count 값을 1 증가
                        keywordRepository.save(keyword);
                    } else {
                        // 새로운 키워드 추가
                        Keyword newKeyword = new Keyword(keywordWord);
                        keywordRepository.save(newKeyword);
                    }
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
