package surfthon.campus_call.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class QuestionRequestDto implements Serializable {
    private String question;

    public QuestionRequestDto(String question) {
        this.question = question;
    }

}