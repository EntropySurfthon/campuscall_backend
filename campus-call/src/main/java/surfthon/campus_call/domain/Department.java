package surfthon.campus_call.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Department {

    @Id
    private Long id;
    private String name;
    private String duty;
    private List<String> keyword;

}
