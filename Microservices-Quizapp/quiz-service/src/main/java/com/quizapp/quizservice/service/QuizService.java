package com.quizapp.quizservice.service;

import com.quizapp.quizservice.dao.QuizDao;
import com.quizapp.quizservice.model.Quiz;
import com.quizapp.quizservice.model.dto.QuestionDto;
import com.quizapp.quizservice.model.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private QuizDao quizDao;
//    private QuestionDao questionDao;

//    @Autowired
//    public void setQuizDao(QuizDao quizDao, QuestionDao questionDao) {
//        this.quizDao = quizDao;
//        this.questionDao = questionDao;
//    }


    @Autowired
    public QuizService(QuizDao quizDao) {
        this.quizDao = quizDao;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

//        List<Integer> question = ; // call to generate the url --> RestTemplate http://localhost:8081/question/generate
//
//        Quiz quiz = new Quiz();
//        quiz.setTitle(title);
//        quiz.setQuestions(question);
//
//        quizDao.save(quiz);

//        return new ResponseEntity<>(quiz.getId(), HttpStatus.CREATED);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionDto>> getQuizQuestions(Integer id) {

        Optional<Quiz> quizOptional = quizDao.findById(id);

        if (quizOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        List<Question> questionsFromDB = quizOptional.get().getQuestions();
        List<QuestionDto> questionsForUser =  new ArrayList<>();

//        for (Question question : questionsFromDB) {
//            QuestionDto dto = new QuestionDto();
//            dto.setId(question.getId());
//            dto.setQuestionTitle(question.getQuestionTitle());
//            dto.setOption1(question.getOption1());
//            dto.setOption2(question.getOption2());
//            dto.setOption3(question.getOption3());
//            dto.setOption4(question.getOption4());
//            questionsForUser.add(dto);
//        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<ResponseDto> responses) {

        Optional<Quiz> quizOptional = quizDao.findById(id);
        if (quizOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        int correctAnswer = 0;

        for (ResponseDto response : responses) {
            Quiz question = quizDao.findById(response.getId()).orElse(null);

            if (question != null && question.getQuestions() != null ) {
                correctAnswer++;
            }
        }
        return new ResponseEntity<>(correctAnswer, HttpStatus.OK);
    }

}
