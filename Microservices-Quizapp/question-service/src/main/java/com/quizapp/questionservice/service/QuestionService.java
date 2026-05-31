package com.quizapp.questionservice.service;

import com.quizapp.questionservice.dao.QuestionDao;
import com.quizapp.questionservice.model.Question;
import com.quizapp.questionservice.model.dto.QuestionDto;
import com.quizapp.questionservice.model.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategoryIgnoreCase(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        try {
            return new ResponseEntity<>(questionDao.save(question), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String deleteQuestion(int id) {
        questionDao.deleteById(id);
        return "Question has been deleted";
    }

    public ResponseEntity<Question> updateQuestion(Question question) {
        try {
            return new ResponseEntity<>(questionDao.save(question), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // getQuestion
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String categoryName, Integer numQuestions) {

        List<Integer> question = questionDao.findRandomQuestionByCategoryIgnoreCase(categoryName, numQuestions);

        return new ResponseEntity<>(question, HttpStatus.CREATED);

    }

    // getQuestion (through questionId)
    public ResponseEntity<List<QuestionDto>> getQuestionsFromId(List<Integer> questionIds) {

        List<QuestionDto> questionDtos = new ArrayList<>();

        List<Question> questions = new ArrayList<>();

        for (Integer questionId : questionIds) {
//            if (questionDao.findById(questionId).isPresent()) {
//                questions.add(questionDao.findById(questionId).get());
//            }
            Optional<Question> question = questionDao.findById(questionId);
            question.ifPresent(questions::add);
        }

        for (Question question : questions) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setId(question.getId());
            questionDto.setQuestionTitle(question.getQuestionTitle());
            questionDto.setOption1(question.getOption1());
            questionDto.setOption2(question.getOption2());
            questionDto.setOption3(question.getOption3());
            questionDto.setOption4(question.getOption4());

            questionDtos.add(questionDto);
        }

        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    // getScore
    public ResponseEntity<Integer> getScore(List<ResponseDto> responseDtos) {

        int correctAnswer = 0;

        for (ResponseDto response : responseDtos) {
            Question question = questionDao.findById(response.getId()).orElse(null);

            if (question != null &&
                    question.getRightAnswer().equalsIgnoreCase(response.getResponse())) {

                correctAnswer++;
            }
        }
        return new ResponseEntity<>(correctAnswer, HttpStatus.OK);
    }
}
