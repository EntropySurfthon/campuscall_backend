package surfthon.campus_call.dto;

public class DepartmentResponseDto {

    private String name;
    private String duty;
    private String pno;
    private String link;

    // 기본 생성자
    public DepartmentResponseDto() {}

    // 생성자
    public DepartmentResponseDto(String name, String duty, String pno, String link) {
        this.name = name;
        this.duty = duty;
        this.pno = pno;
        this.link = link;
    }

    // Getter 및 Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
