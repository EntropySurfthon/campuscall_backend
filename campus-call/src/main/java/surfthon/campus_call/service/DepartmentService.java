package surfthon.campus_call.service;

import org.springframework.stereotype.Service;
import surfthon.campus_call.domain.Department;
import surfthon.campus_call.dto.DepartmentResponseDto;
import surfthon.campus_call.repository.DepartmentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentResponseDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(department -> new DepartmentResponseDto(
                        department.getName(),
                        department.getDuty(),
                        department.getPno(),
                        department.getLink()
                ))
                .collect(Collectors.toList());
    }
}
