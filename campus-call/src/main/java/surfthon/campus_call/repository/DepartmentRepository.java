package surfthon.campus_call.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import surfthon.campus_call.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
