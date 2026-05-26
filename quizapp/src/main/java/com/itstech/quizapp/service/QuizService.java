package com.itstech.quizapp.service;

import com.itstech.quizapp.dao.QuestionDao;
import com.itstech.quizapp.dao.QuizDao;
import com.itstech.quizapp.model.Question;
import com.itstech.quizapp.model.Quiz;
import com.itstech.quizapp.model.dto.QuestionDto;
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
    private QuestionDao questionDao;

    @Autowired
    public void setQuizDao(QuizDao quizDao, QuestionDao questionDao) {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> question = questionDao.findRandomQuestionByCategoryIgnoreCase(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(question);

        quizDao.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionDto>> getQuizQuestions(Integer id) {

        Optional<Quiz> quizOptional = quizDao.findById(id);

        if (quizOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Question> questionsFromDB = quizOptional.get().getQuestions();
        List<QuestionDto> questionsForUser =  new ArrayList<>();

        for (Question question : questionsFromDB) {
            QuestionDto dto = new QuestionDto();
            dto.setId(question.getId());
            dto.setQuestionTitle(question.getQuestionTitle());
            dto.setOption1(question.getOption1());
            dto.setOption2(question.getOption2());
            dto.setOption3(question.getOption3());
            dto.setOption4(question.getOption4());
            questionsForUser.add(dto);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    /*public Optional<Quiz> getQuizById(int id) {
        return quizDao.findById(id);
    }*/
}
