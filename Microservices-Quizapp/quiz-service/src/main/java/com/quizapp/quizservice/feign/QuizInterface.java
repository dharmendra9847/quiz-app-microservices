package com.quizapp.quizservice.feign;

import com.quizapp.quizservice.model.dto.QuestionDto;
import com.quizapp.quizservice.model.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    // generate
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions);

    // getQuestion (through questionId)
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionDto>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    // getScore
    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<ResponseDto> responseDtos);

}
