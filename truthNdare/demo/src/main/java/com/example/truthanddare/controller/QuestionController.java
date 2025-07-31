package com.example.truthanddare.controller;

import com.example.truthanddare.model.Question;
import com.example.truthanddare.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/")
    public String index() {
        return "index"; // loads index.html
    }
    @GetMapping("/mode")
    public String mode() {
        return "mode"; // This will render mode.html
    }

    @GetMapping("/category")
    public String category(@RequestParam String type, Model model) {
        model.addAttribute("type", type);
        return "category"; // loads category.html
    }

    @GetMapping("/play")
    public String play(@RequestParam String type,
                       @RequestParam String category,
                       Model model) {
        Question question = questionRepository.findRandomByTypeAndCategory(type, category);
        model.addAttribute("question", question);
        model.addAttribute("type", type);
        return "play"; // loads play.html
    }

    @GetMapping("/add")
    public String addPage() {
        return "add"; // loads add.html
    }

    @PostMapping("/add")
    public String addQuestion(@RequestParam String content,
                              @RequestParam String type,
                              @RequestParam String category) {
        Question question = new Question();
        question.setContent(content);
        question.setType(type);
        question.setCategory(category);
        questionRepository.save(question);
        return "redirect:/";
    }
}
