package com.itstech.quizapp.service;

import com.itstech.quizapp.dao.QuestionDao;
import com.itstech.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        return new ResponseEntity<>(questionDao.findByCategoryIgnoreCase(category), HttpStatus.OK);
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        return new ResponseEntity<>(questionDao.save(question), HttpStatus.OK);
    }

    public void deleteQuestion(Question question) {
        questionDao.delete(question);
    }

    public ResponseEntity<Question> updateQuestion(Question question) {
            return new ResponseEntity<>(questionDao.save(question), HttpStatus.OK);
    }
}
