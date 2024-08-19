package surfthon.campus_call.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Department {

    @Id
    private Long id;
    private String name;
    private String link;
    private String duty;
    private String pno;
    private String keyword;

}
