package com.quizapp.quizservice.controller;


import com.quizapp.quizservice.model.dto.QuestionDto;
import com.quizapp.quizservice.model.dto.QuizDto;
import com.quizapp.quizservice.model.dto.ResponseDto;
import com.quizapp.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@CrossOrigin
public class QuizController {

    private QuizService quizService;

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
    }

    // FETCHING THE QUIZ-QUESTIONS BY ID
    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<QuestionDto>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    // CALCULATE RESULT AND SUBMIT THE RESPONSE
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<ResponseDto> responses) {
        return quizService.calculateResult(id, responses);
    }
}
