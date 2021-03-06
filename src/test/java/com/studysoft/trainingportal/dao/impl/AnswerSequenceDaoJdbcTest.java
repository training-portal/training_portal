package com.studysoft.trainingportal.dao.impl;

import com.studysoft.trainingportal.config.ApplicationConfiguration;
import com.studysoft.trainingportal.dao.AnswerSequenceDao;
import com.studysoft.trainingportal.dao.QuestionDao;
import com.studysoft.trainingportal.model.AnswerSequence;
import com.studysoft.trainingportal.model.Question;
import com.studysoft.trainingportal.model.enums.QuestionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:dump_postgres.sql"})
public class AnswerSequenceDaoJdbcTest {

    @Autowired
    private AnswerSequenceDao answerSequenceDao;

    @Autowired
    private QuestionDao questionDao;

    public void setAnswerSequenceDao(AnswerSequenceDao answerSequenceDao) {
        this.answerSequenceDao = answerSequenceDao;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Test
    public void find_answerSequence_by_questionId() {
        AnswerSequence testAnswerSequence = new AnswerSequence.AnswerSequenceBuilder()
                .questionId(4L)
                .correctList(Arrays.asList("Pascal", "C++",
                        "Java", "Angular JS"))
                .build();

        AnswerSequence answerSequence =
                answerSequenceDao.findAnswerSequence(4L);

        assertEquals(testAnswerSequence, answerSequence);
    }

    @Test
    public void addAnswerSequence() {
        Question testQuestion = new Question.QuestionBuilder()
                .quizId(1L)
                .body("Question 1.6 body?")
                .explanation("Question 1.6 explanation")
                .questionType(QuestionType.ONE_ANSWER)
                .score(1)
                .build();
        Long questionId = questionDao.addQuestion(testQuestion);

        AnswerSequence testAnswerSequence = new AnswerSequence.AnswerSequenceBuilder()
                .questionId(questionId)
                .correctList(Arrays.asList("1", "2", "3", "4"))
                .build();
        answerSequenceDao.addAnswerSequence(testAnswerSequence);

        AnswerSequence answerSequence =
                answerSequenceDao.findAnswerSequence(questionId);

        assertEquals(testAnswerSequence, answerSequence);
    }

    @Test
    public void editAnswerSequence() {
        AnswerSequence testAnswerSequence = new AnswerSequence.AnswerSequenceBuilder()
                .questionId(4L)
                .correctList(Arrays.asList("1", "2", "3", "4"))
                .build();
        answerSequenceDao.editAnswerSequence(testAnswerSequence);

        AnswerSequence answerSequence =
                answerSequenceDao.findAnswerSequence(4L);

        assertEquals(testAnswerSequence, answerSequence);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void deleteAnswerSequence() {
        answerSequenceDao.deleteAnswerSequence(4L);
        answerSequenceDao.findAnswerSequence(4L);
    }
}