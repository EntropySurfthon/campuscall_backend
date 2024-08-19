package surfthon.campus_call.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import surfthon.campus_call.dto.ChatGptRequestDto;
import surfthon.campus_call.dto.ChatGptResponseDto;
import surfthon.campus_call.dto.QuestionRequestDto;
import surfthon.campus_call.service.ChatGptService;

@Controller
public class ChatGptController {
    private final ChatGptService chatGptService;

    public ChatGptController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @GetMapping("/chat-gpt")
    public String showChatPage() {
        return "chat-gpt";
    }

    /*
    @PostMapping("/chat-gpt/question")
    public ChatGptResponseDto sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        return chatGptService.askQuestion(requestDto);
    }
     */

    @PostMapping("/chat-gpt/question")
    public String askQuestion(@RequestParam("question") String question, Model model) {
        QuestionRequestDto requestDto = new QuestionRequestDto(question);
        ChatGptResponseDto responseDto = chatGptService.askQuestion(requestDto);
        String answer = responseDto.getChoices().get(0).getText();

        model.addAttribute("question", question);
        model.addAttribute("answer", answer);
        return "chat-gpt";
    }
}
