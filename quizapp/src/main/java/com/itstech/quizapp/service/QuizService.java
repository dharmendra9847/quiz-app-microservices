package com.itstech.quizapp.service;

import com.itstech.quizapp.dao.QuestionDao;
import com.itstech.quizapp.dao.QuizDao;
import com.itstech.quizapp.model.Question;
import com.itstech.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
