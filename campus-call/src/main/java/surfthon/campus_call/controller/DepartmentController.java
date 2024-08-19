package surfthon.campus_call.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import surfthon.campus_call.dto.DepartmentResponseDto;
import surfthon.campus_call.service.DepartmentService;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/api/departments")
    @ResponseBody
    public List<DepartmentResponseDto> getDepartments() {
        return departmentService.getAllDepartments();
    }
}
