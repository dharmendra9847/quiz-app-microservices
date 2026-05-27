package com.itstech.quizapp.controller;

import com.itstech.quizapp.model.dto.ResponseDto;
import com.itstech.quizapp.model.dto.QuestionDto;
import com.itstech.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private QuizService quizService;

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(category, numQ, title);
    }

    // FETCHING THE QUIZ BY ID
    /*@GetMapping("getQuiz/{id}")
    public Optional<Quiz> getQuizById(@PathVariable int id) {
        return quizService.getQuizById(id);
    }*/

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
