package com.studysoft.trainingportal.dao;

import com.studysoft.trainingportal.model.OpenedQuiz;
import com.studysoft.trainingportal.model.PassedQuiz;
import com.studysoft.trainingportal.model.Quiz;
import com.studysoft.trainingportal.model.enums.StudentQuizStatus;
import com.studysoft.trainingportal.model.enums.TeacherQuizStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface QuizDao {

    Quiz findQuiz(Long quizId);

    List<Quiz> findAllQuizzesWithQuestions();


    List<Quiz> findUnpublishedQuizzes(Long teacherId);

    List<Quiz> findPublishedQuizzes(Long teacherId);

    List<Quiz> findPublishedQuizzes(Long groupId, Long teacherId);

    List<Quiz> findStudentQuizzes(Long studentId);

    // No usages
    Integer findQuizzesNumber(Long authorId);

    // key: studentQuizStatus, value: number of students
    Map<StudentQuizStatus, Integer> findStudentsNumberWithStudentQuizStatus(
            Long authorId, Long groupId, Long quizId);

    // No usages
    // key: teacherQuizStatus, value: number of quizzes
    Map<TeacherQuizStatus, Integer> findQuizzesNumberByAuthorIdWithTeacherQuizStatus(Long authorId);

    List<Quiz> findQuizzes(Long studentId, Long authorId);

    List<Long> findCommonQuizIds(Long studentId1, Long studentId2);

    List<Long> findTeacherQuizIds(Long authorId);

    List<Long> findStudentQuizIds(Long studentId);

    Integer findResult(Long studentId, Long quizId);

    LocalDateTime findSubmitDate(Long studentId, Long quizId);

    LocalDateTime findStartDate(Long studentId, Long quizId);

    LocalDateTime findFinishDate(Long studentId, Long quizId);

    Integer findAttempt(Long studentId, Long quizId);

    StudentQuizStatus findStudentQuizStatus(Long studentId, Long quizId);

    LocalDateTime findClosingDate(Long groupId, Long quizId);

    OpenedQuiz findOpenedQuiz(Long studentId, Long quizId);

    PassedQuiz findPassedQuiz(Long studentId, Long quizId);

    PassedQuiz findClosedQuiz(Long studentId, Long quizId);

    List<OpenedQuiz> findOpenedQuizzes(Long studentId);

    List<PassedQuiz> findPassedQuizzes(Long studentId);

    List<PassedQuiz> findClosedQuizzes(Long studentId);

    List<OpenedQuiz> findOpenedQuizzes(Long studentId, Long teacherId);

    List<PassedQuiz> findPassedQuizzes(Long studentId, Long teacherId);

    List<PassedQuiz> findClosedQuizzes(Long studentId, Long teacherId);

    boolean quizExistsByName(String name);

    void addPublishedQuizInfo(List<Long> studentIds, Long quizId);

    Long addQuiz(Quiz quiz);

    // Need test
    void editStartDate(LocalDateTime startDate, Long studentId, Long quizId);

    void editStudentInfoAboutOpenedQuiz(Long studentId, Long quizId,
                                        Integer result, LocalDateTime finishDate,
                                        Integer attempt, StudentQuizStatus studentQuizStatus);

    void editTeacherQuizStatus(TeacherQuizStatus teacherQuizStatus, Long quizId);

    void editQuiz(Long quizId, String name, String description,
                  String explanation, Duration passingTime);

    void editStudentsInfoWithOpenedQuizStatus(Long groupId, Long quizId);

    void closeQuizToStudent(Long studentId, Long quizId);

    void closeQuizToGroup(Long groupId, Long quizId);

    void deleteUnpublishedQuiz(Long quizId);

    void deleteStudentsInfoAboutQuiz(Long quizId);
}
