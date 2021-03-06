package com.studysoft.trainingportal.dao.impl;

import com.studysoft.trainingportal.dao.UserDao;
import com.studysoft.trainingportal.model.User;
import com.studysoft.trainingportal.model.enums.StudentQuizStatus;
import com.studysoft.trainingportal.model.enums.UserRole;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static com.studysoft.trainingportal.util.Utils.formatPhoneNumber;

@Repository
public class UserDaoJdbc implements UserDao {

    private JdbcTemplate template;

    private static final Logger logger = Logger.getLogger(UserDaoJdbc.class);

    @Autowired
    public UserDaoJdbc(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUser(Long userId) {
        User user = template.queryForObject(FIND_USER_BY_USER_ID,
                new Object[]{userId}, this::mapUser);
        logger.info("User found by userId: " + user);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByLogin(String login) {
        User user = template.queryForObject(FIND_USER_BY_LOGIN,
                new Object[]{login}, this::mapUser);
        logger.info("User found by login: " + user);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByEmail(String email) {
        User user = template.queryForObject(FIND_USER_BY_EMAIL,
                new Object[]{email}, this::mapUser);
        logger.info("User found by email: " + user);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        User user = template.queryForObject(FIND_USER_BY_PHONE_NUMBER,
                new Object[]{phoneNumber}, this::mapUser);
        logger.info("User found by phoneNumber: " + user);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public String findUserName(Long userId) {
        String name = template.queryForObject(FIND_USER_NAME_BY_USER_ID,
                new Object[]{userId}, new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet rs, int i) throws SQLException {
                        String lastName = rs.getString("last_name");
                        String firstName = rs.getString("first_name");
                        return lastName + " " + firstName;
                    }
                });
        logger.info("Found userName by userId: " + name);
        return name;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findUsers(String firstName,
                                String lastName,
                                UserRole userRole) {
        List<User> users = template.query(FIND_USERS_BY_FIRST_NAME_AND_LAST_NAME_AND_USER_ROLE,
                new Object[]{firstName, lastName, userRole.getRole()}, this::mapUser);
        logger.info("Users found by firstName, lastName, userRole:");
        users.forEach(logger::info);
        return users;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findStudents(Long groupId) {
        List<User> students = template.query(FIND_STUDENTS_BY_GROUP_ID,
                new Object[]{groupId}, this::mapUser);
        logger.info("Students found by groupId:");
        students.forEach(logger::info);
        return students;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findStudents(Long groupId, Long quizId) {
        List<User> students = template.query(FIND_STUDENTS_BY_GROUP_ID_AND_QUIZ_ID,
                new Object[]{groupId, quizId}, this::mapUser);
        logger.info("Found students by groupId '" + groupId + "' and quizId '" + quizId + "':");
        students.forEach(logger::info);
        return students;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findStudents(Long groupId, Long quizId, StudentQuizStatus status) {
        List<User> students =
                template.query(FIND_STUDENTS_BY_GROUP_ID_AND_QUIZ_ID_AND_STUDENT_QUIZ_STATUS,
                new Object[]{groupId, quizId, status.getStudentQuizStatus()},
                this::mapUser);
        logger.info("Found students by groupId '" + groupId + "', quizId '" + quizId +
        "' and studentQuizStatus '" + status.getStudentQuizStatus() + "':");
        students.forEach(logger::info);
        return students;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findStudentsByTeacherId(Long teacherId) {
        List<User> students = template.query(FIND_STUDENTS_BY_TEACHER_ID,
                new Object[]{teacherId}, this::mapUser);
        logger.info("Found students by teacherId:");
        students.forEach(logger::info);
        return students;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllStudents() {
        List<User> students = template.query(FIND_ALL_STUDENTS, this::mapUser);
        logger.info("All students found:");
        students.forEach(logger::info);
        return students;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findStudentsWithoutGroup() {
        List<User> students = template.query(FIND_STUDENTS_WITHOUT_GROUP, this::mapUser);
        logger.info("Found students without group:");
        students.forEach(logger::info);
        return students;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findStudentsWithoutGroup(Long teacherId) {
        List<User> students = template.query(FIND_STUDENTS_WITHOUT_GROUP_BY_TEACHER_ID,
                new Object[]{teacherId}, this::mapUser);
        logger.info("Found students without group by teacherId '" + teacherId + "' :");
        students.forEach(logger::info);
        return students;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findStudentsWithoutGroupForQuizPublication(Long quizId) {
        List<User> students = template.query(FIND_STUDENTS_WITHOUT_GROUP_FOR_QUIZ_PUBLICATION,
                new Object[]{quizId}, this::mapUser);
        logger.info("Found students without group for quiz publication by quizId = " + quizId + ":");
        students.forEach(logger::info);
        return students;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findStudentsForQuizPublication(Long groupId, Long quizId) {
        List<User> students = template.query(FIND_STUDENTS_FOR_QUIZ_PUBLICATION_BY_GROUP_ID_AND_QUIZ_ID,
                new Object[]{groupId, quizId}, this::mapUser);
        logger.info("Found students for quiz publication by groupId = " +
                groupId + " and quiz id = " + quizId + ":");
        students.forEach(logger::info);
        return students;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findStudentsWithoutGroupForWhomPublished(Long quizId) {
        List<User> students = template.query(
                FIND_STUDENTS_WITHOUT_GROUP_FOR_WHOM_PUBLISHED_BY_QUIZ_ID,
                new Object[]{quizId}, this::mapUser);
        logger.info("Found all students without group for whom published by quizId '" + quizId + "':");
        students.forEach(logger::info);
        return students;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findStudentsForWhomPublished(Long groupId, Long quizId) {
        List<User> students = template.query(
                FIND_STUDENTS_FOR_WHOM_PUBLISHED_BY_GROUP_ID_AND_QUIZ_ID,
                new Object[]{groupId, quizId}, this::mapUser);
        logger.info("Found all students for whom published by groupId '" + groupId +
                "' and quizId '" + quizId + "':");
        students.forEach(logger::info);
        return students;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllTeachers() {
        List<User> teachers = template.query(FIND_ALL_TEACHERS, this::mapUser);
        logger.info("All teachers found:");
        teachers.forEach(logger::info);
        return teachers;
    }

    @Transactional(readOnly = true)
    @Override
    public Integer findStudentsNumber() {
        Integer studentsNumber = template.queryForObject(FIND_STUDENTS_NUMBER, Integer.class);
        logger.info("Found students number: " + studentsNumber);
        return studentsNumber;
    }

    @Override
    public Integer findStudentsNumberToWhomQuizWasPublished(Long quizId) {
        Integer studentsNumber = template.queryForObject(
                FIND_STUDENTS_NUMBER_TO_WHOM_QUIZ_WAS_PUBLISHED,
                new Object[]{quizId}, Integer.class);
        logger.info("Found students number to whom quiz was published by quizId = " +
                quizId + ": " + studentsNumber);
        return studentsNumber;
    }

    @Override
    public Integer findStudentsNumberWhoClosedQuiz(Long quizId) {
        Integer studentsNumber = template.queryForObject(
                FIND_STUDENTS_NUMBER_WHO_CLOSED_QUIZ,
                new Object[]{quizId}, Integer.class);
        logger.info("Found students number who closed quiz by quizId = " +
                quizId + ": " + studentsNumber);
        return studentsNumber;
    }

    @Transactional(readOnly = true)
    @Override
    public Integer findStudentsNumber(Long groupId, Long quizId) {
        Integer studentsNumber = template.queryForObject(FIND_STUDENTS_NUMBER_BY_GROUP_ID_AND_QUIZ_ID,
                new Object[]{groupId, quizId}, Integer.class);
        logger.info("Found students number by groupId '" + groupId +
                "' and quizId '" + quizId + "': " + studentsNumber);
        return studentsNumber;
    }

    @Transactional(readOnly = true)
    @Override
    public Integer findTeachersNumber() {
        Integer teachersNumber = template.queryForObject(FIND_TEACHERS_NUMBER, Integer.class);
        logger.info("Found teachers number: " + teachersNumber);
        return teachersNumber;
    }

    @Transactional(readOnly = true)
    @Override
    public Integer findStudentsNumberInGroupWithClosedQuiz(Long groupId, Long quizId) {
        Integer studentsNumber = template.queryForObject(
                FIND_STUDENTS_NUMBER_IN_GROUP_WITH_CLOSED_QUIZ,
                new Object[]{groupId, quizId}, Integer.class);
        logger.info("Students number in group with closed quiz found: " + studentsNumber);
        return studentsNumber;
    }

    @Transactional(readOnly = true)
    @Override
    public Integer findResultsNumber(Long groupId, Long quizId) {
        Integer resultsNumber = template.queryForObject(
                FIND_RESULTS_NUMBER_BY_GROUP_ID_AND_QUIZ_ID,
                new Object[]{groupId, quizId}, Integer.class);
        logger.info("Results number by groupId and quizId found: " + resultsNumber);
        return resultsNumber;
    }

    @Transactional(readOnly = true)
    @Override
    public Integer findFinalResultsNumber(Long groupId, Long quizId) {
        Integer resultsNumber = template.queryForObject(
                FIND_FINAL_RESULTS_NUMBER_BY_GROUP_ID_AND_QUIZ_ID,
                new Object[]{groupId, quizId}, Integer.class);
        logger.info("Final results number by groupId and quizId found: " + resultsNumber);
        return resultsNumber;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean userExistsByLogin(String login) {
        User user = template.query(FIND_USER_BY_LOGIN, new Object[]{login},
                new ResultSetExtractor<User>() {
                    @Override
                    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (!rs.next()) {
                            return null;
                        }
                        return mapUser(rs, 0);
                    }
                });
        if (user == null) {
            logger.info("No user exists by login: " + login);
            return false;
        }
        logger.info("User exists by login: " + login);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean userExistsByEmail(String email) {
        User user = template.query(FIND_USER_BY_EMAIL, new Object[]{email},
                new ResultSetExtractor<User>() {
                    @Override
                    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (!rs.next()) {
                            return null;
                        }
                        return mapUser(rs, 0);
                    }
                });
        if (user == null) {
            logger.info("No user exists by email: " + email);
            return false;
        }
        logger.info("User exists by email: " + email);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean userExistsByPhoneNumber(String phoneNumber) {
        User user = template.query(FIND_USER_BY_PHONE_NUMBER, new Object[]{phoneNumber},
                new ResultSetExtractor<User>() {
                    @Override
                    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (!rs.next()) {
                            return null;
                        }
                        return mapUser(rs, 0);
                    }
                });
        if (user == null) {
            logger.info("No user exists by phoneNumber: " + phoneNumber);
            return false;
        }
        logger.info("User exists by phoneNumber: " + phoneNumber);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public User checkUserByLoginAndPassword(String login, String password) {
        User user = template.query(FIND_USER_BY_LOGIN_AND_PASSWORD,
                new Object[]{login, password},
                new ResultSetExtractor<User>() {
                    @Override
                    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (!rs.next()) {
                            return null;
                        }
                        return mapUser(rs, 0);
                    }
                });
        logger.info("User by login and password found: " + user);
        return user;
    }

    @Transactional
    @Override
    public Long registerUser(User user) {
        user.setPhoneNumber(formatPhoneNumber(user.getPhoneNumber()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
            template.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement stmt = con.prepareStatement(REGISTER_USER, new String[]{"user_id"});
                    stmt.setString(1, user.getFirstName());
                    stmt.setString(2, user.getLastName());
                    stmt.setString(3, user.getEmail());
                    stmt.setDate(4,
                            (user.getDateOfBirth() == null) ? null : Date.valueOf(user.getDateOfBirth()));
                    stmt.setString(5, user.getPhoneNumber());
                    stmt.setString(6, user.getLogin());
                    stmt.setString(7, user.getPassword());
                    stmt.setString(8, user.getUserRole().getRole());
                    return stmt;
                }
            }, keyHolder);
        long userId = keyHolder.getKey().longValue();
        user.setUserId(userId);
        logger.info("User registered: " + user);
        return userId;
    }

    @Transactional
    @Override
    public void addStudentToGroup(Long groupId, Long studentId) {
        template.update(ADD_STUDENT_TO_GROUP_BY_GROUP_ID_AND_USER_ID, groupId, studentId);
        logger.info("Student with id = " + studentId +
                " added to group with id = '" + groupId + "'");
    }

    @Transactional
    @Override
    public void addStudentsToGroup(Long groupId, List<Long> studentIds) {
        for (Long id : studentIds) {
            template.update(ADD_STUDENT_TO_GROUP_BY_GROUP_ID_AND_USER_ID, groupId, id);
        }
        logger.info("Students with ids = " + studentIds +
                " added to group with id = '" + groupId + "'");
    }

    @Transactional
    @Override
    public void editUser(Long userId, String firstName, String lastName, String email,
                         LocalDate dateOfBirth, String phoneNumber, String password) {
        Date birthDate = null;
        String formattedPhoneNumber = formatPhoneNumber(phoneNumber);
        if (dateOfBirth != null) {
            birthDate = Date.valueOf(dateOfBirth);
        }
        template.update(EDIT_USER, firstName, lastName, email, birthDate,
                formattedPhoneNumber, password, userId);
        logger.info("Edited user by userId = " + userId +
                ": firstName: " + firstName +
        ", lastName: " + lastName +
        ", email: " + email +
        ", dateOfBirth: " + dateOfBirth +
        ", phoneNumber: " + formattedPhoneNumber +
        ", password: " + password);
    }

    @Transactional
    @Override
    public void deleteStudentFromGroupByUserId(Long userId) {
        template.update(DELETE_STUDENT_FROM_GROUP_BY_USER_ID, userId);
        logger.info("Student deleted from group by userId: " + userId);
    }

    @Transactional
    @Override
    public void deleteStudentsFromGroupByGroupId(Long groupId) {
        template.update(DELETE_STUDENTS_FROM_GROUP_BY_GROUP_ID, groupId);
        logger.info("Students deleted form group by groupId: " + groupId);
    }

    private User mapUser(ResultSet rs, int rowNum) throws SQLException {
        Date DbBirthDate = rs.getDate("date_of_birth");
        LocalDate birthDate = null;
        if (DbBirthDate != null) {
            birthDate = DbBirthDate.toLocalDate();
        }
        return new User.UserBuilder()
                .userId(rs.getLong("user_id"))
                .groupId(rs.getLong("group_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .dateOfBirth(birthDate)
                .phoneNumber(rs.getString("phone_number"))
                .photo(null)
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .userRole(UserRole.valueOf(rs.getString("user_role")))
                .build();
    }

    private static final String FIND_USER_BY_USER_ID = "SELECT * FROM USERS WHERE USER_ID = ?;";

    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM USERS WHERE LOGIN = ?;";

    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM USERS WHERE EMAIL = ?;";

    private static final String FIND_USER_BY_PHONE_NUMBER = "SELECT * FROM USERS WHERE PHONE_NUMBER = ?;";

    private static final String FIND_USER_NAME_BY_USER_ID =
    "SELECT FIRST_NAME, LAST_NAME FROM USERS WHERE USER_ID = ?;";

    private static final String FIND_USERS_BY_FIRST_NAME_AND_LAST_NAME_AND_USER_ROLE =
    "SELECT * FROM USERS WHERE FIRST_NAME = ? AND LAST_NAME = ? AND USER_ROLE = ?";

    private static final String FIND_STUDENTS_BY_GROUP_ID =
    "SELECT * " +
    "FROM USERS " +
    "WHERE GROUP_ID = ? AND USER_ROLE = 'STUDENT' " +
    "ORDER BY LAST_NAME, FIRST_NAME;";

    private static final String FIND_STUDENTS_BY_GROUP_ID_AND_QUIZ_ID =
    "SELECT USERS.USER_ID, USERS.GROUP_ID, USERS.FIRST_NAME, USERS.LAST_NAME, USERS.EMAIL, " +
    "USERS.DATE_OF_BIRTH, USERS.PHONE_NUMBER, USERS.PHOTO, USERS.LOGIN, USERS.PASSWORD, USERS.USER_ROLE " +
    "FROM USERS INNER JOIN USER_QUIZ_JUNCTIONS J ON USERS.USER_ID = J.USER_ID " +
    "WHERE USERS.GROUP_ID = ? AND J.QUIZ_ID = ? AND USERS.USER_ROLE = 'STUDENT' " +
    "ORDER BY USERS.LAST_NAME, USERS.FIRST_NAME;";

    private static final String FIND_STUDENTS_BY_GROUP_ID_AND_QUIZ_ID_AND_STUDENT_QUIZ_STATUS =
    "SELECT USERS.USER_ID, USERS.GROUP_ID, USERS.FIRST_NAME, USERS.LAST_NAME, USERS.EMAIL, " +
    "USERS.DATE_OF_BIRTH, USERS.PHONE_NUMBER, USERS.PHOTO, USERS.LOGIN, USERS.PASSWORD, USERS.USER_ROLE " +
    "FROM USERS INNER JOIN USER_QUIZ_JUNCTIONS J ON USERS.USER_ID = J.USER_ID " +
    "WHERE USERS.GROUP_ID = ? AND J.QUIZ_ID = ? " +
    "AND J.STUDENT_QUIZ_STATUS = ? AND USERS.USER_ROLE = 'STUDENT';";

    private static final String FIND_STUDENTS_BY_TEACHER_ID =
    "SELECT USERS.USER_ID, USERS.GROUP_ID, USERS.FIRST_NAME, USERS.LAST_NAME, USERS.EMAIL, " +
    "USERS.DATE_OF_BIRTH, USERS.PHONE_NUMBER, USERS.PHOTO, USERS.LOGIN, USERS.PASSWORD, USERS.USER_ROLE " +
    "FROM USERS INNER JOIN USER_QUIZ_JUNCTIONS J ON USERS.USER_ID = J.USER_ID " +
    "INNER JOIN QUIZZES ON J.QUIZ_ID = QUIZZES.QUIZ_ID " +
    "WHERE QUIZZES.AUTHOR_ID = ? AND USER_ROLE = 'STUDENT' " +
    "GROUP BY USERS.USER_ID " +
    "ORDER BY USERS.LAST_NAME, USERS.FIRST_NAME;";

    private static final String FIND_ALL_STUDENTS =
    "SELECT * FROM USERS WHERE USER_ROLE = 'STUDENT';";

    private static final String FIND_ALL_TEACHERS =
    "SELECT * FROM USERS WHERE USER_ROLE = 'TEACHER';";

    private static final String FIND_STUDENTS_WITHOUT_GROUP =
    "SELECT * FROM USERS WHERE GROUP_ID IS NULL AND USER_ROLE = 'STUDENT' ORDER BY LAST_NAME, FIRST_NAME;";

    private static final String FIND_STUDENTS_WITHOUT_GROUP_BY_TEACHER_ID =
    "SELECT USERS.USER_ID, USERS.GROUP_ID, USERS.FIRST_NAME, USERS.LAST_NAME, USERS.EMAIL, " +
    "USERS.DATE_OF_BIRTH, USERS.PHONE_NUMBER, USERS.PHOTO, USERS.LOGIN, USERS.PASSWORD, USERS.USER_ROLE " +
    "FROM USERS INNER JOIN USER_QUIZ_JUNCTIONS J ON USERS.USER_ID = J.USER_ID " +
    "INNER JOIN QUIZZES ON J.QUIZ_ID = QUIZZES.QUIZ_ID " +
    "WHERE QUIZZES.AUTHOR_ID = ? AND USERS.USER_ROLE = 'STUDENT' AND USERS.GROUP_ID IS NULL " +
    "GROUP BY USERS.USER_ID " +
    "ORDER BY USERS.LAST_NAME, USERS.FIRST_NAME;";

    private static final String FIND_STUDENTS_WITHOUT_GROUP_FOR_QUIZ_PUBLICATION =
    "SELECT USERS.USER_ID, USERS.GROUP_ID, USERS.FIRST_NAME, USERS.LAST_NAME, USERS.EMAIL, " +
    "USERS.DATE_OF_BIRTH, USERS.PHONE_NUMBER, USERS.PHOTO, USERS.LOGIN, USERS.PASSWORD, USERS.USER_ROLE " +
    "FROM USERS LEFT JOIN USER_QUIZ_JUNCTIONS J ON USERS.USER_ID = J.USER_ID " +
    "WHERE USERS.USER_ROLE = 'STUDENT' AND USERS.GROUP_ID IS NULL AND " +
    "USERS.USER_ID NOT IN (SELECT J.USER_ID FROM USER_QUIZ_JUNCTIONS J WHERE J.QUIZ_ID = ?) " +
    "GROUP BY USERS.USER_ID " +
    "ORDER BY USERS.LAST_NAME, USERS.FIRST_NAME;";

    private static final String FIND_STUDENTS_FOR_QUIZ_PUBLICATION_BY_GROUP_ID_AND_QUIZ_ID =
    "SELECT USERS.USER_ID, USERS.GROUP_ID, USERS.FIRST_NAME, USERS.LAST_NAME, USERS.EMAIL, " +
    "USERS.DATE_OF_BIRTH, USERS.PHONE_NUMBER, USERS.PHOTO, USERS.LOGIN, USERS.PASSWORD, USERS.USER_ROLE " +
    "FROM USERS LEFT JOIN USER_QUIZ_JUNCTIONS J ON USERS.USER_ID = J.USER_ID " +
    "WHERE USERS.USER_ROLE = 'STUDENT' AND USERS.GROUP_ID = ? AND " +
    "USERS.USER_ID NOT IN (SELECT J.USER_ID FROM USER_QUIZ_JUNCTIONS J WHERE J.QUIZ_ID = ?) " +
    "GROUP BY USERS.USER_ID " +
    "ORDER BY USERS.LAST_NAME, USERS.FIRST_NAME;";

    private static final String FIND_STUDENTS_WITHOUT_GROUP_FOR_WHOM_PUBLISHED_BY_QUIZ_ID =
    "SELECT USERS.USER_ID, USERS.GROUP_ID, USERS.FIRST_NAME, USERS.LAST_NAME, USERS.EMAIL, " +
    "USERS.DATE_OF_BIRTH, USERS.PHONE_NUMBER, USERS.PHOTO, USERS.LOGIN, USERS.PASSWORD, USERS.USER_ROLE " +
    "FROM USERS INNER JOIN USER_QUIZ_JUNCTIONS J ON USERS.USER_ID = J.USER_ID " +
    "WHERE USERS.USER_ROLE = 'STUDENT' AND USERS.GROUP_ID IS NULL AND J.QUIZ_ID = ? " +
    "GROUP BY USERS.USER_ID " +
    "ORDER BY USERS.LAST_NAME, USERS.FIRST_NAME;";

    private static final String FIND_STUDENTS_FOR_WHOM_PUBLISHED_BY_GROUP_ID_AND_QUIZ_ID =
    "SELECT USERS.USER_ID, USERS.GROUP_ID, USERS.FIRST_NAME, USERS.LAST_NAME, USERS.EMAIL, " +
    "USERS.DATE_OF_BIRTH, USERS.PHONE_NUMBER, USERS.PHOTO, USERS.LOGIN, USERS.PASSWORD, USERS.USER_ROLE " +
    "FROM USERS INNER JOIN USER_QUIZ_JUNCTIONS J ON USERS.USER_ID = J.USER_ID " +
    "WHERE USERS.USER_ROLE = 'STUDENT' AND USERS.GROUP_ID = ? AND J.QUIZ_ID = ? " +
    "GROUP BY USERS.USER_ID " +
    "ORDER BY USERS.LAST_NAME, USERS.FIRST_NAME;";

    private static final String FIND_STUDENTS_NUMBER =
    "SELECT COUNT(USER_ID) FROM USERS WHERE USER_ROLE = 'STUDENT';";

    private static final String FIND_STUDENTS_NUMBER_TO_WHOM_QUIZ_WAS_PUBLISHED =
    "SELECT COUNT(USER_ID) FROM USER_QUIZ_JUNCTIONS WHERE QUIZ_ID = ?;";

    private static final String FIND_STUDENTS_NUMBER_WHO_CLOSED_QUIZ =
    "SELECT COUNT(USER_ID) FROM USER_QUIZ_JUNCTIONS " +
    "WHERE QUIZ_ID = ? AND STUDENT_QUIZ_STATUS = 'CLOSED';";

    private static final String FIND_STUDENTS_NUMBER_BY_GROUP_ID_AND_QUIZ_ID =
    "SELECT COUNT(USERS.USER_ID) " +
    "FROM USERS INNER JOIN USER_QUIZ_JUNCTIONS J ON USERS.USER_ID = J.USER_ID " +
    "WHERE CASE WHEN USERS.GROUP_ID IS NULL THEN FALSE ELSE USERS.GROUP_ID = ? AND J.QUIZ_ID = ? END;";

    private static final String FIND_TEACHERS_NUMBER =
    "SELECT COUNT(USER_ID) FROM USERS WHERE USER_ROLE = 'TEACHER';";

    private static final String FIND_STUDENTS_NUMBER_IN_GROUP_WITH_CLOSED_QUIZ =
    "SELECT COUNT(USERS.USER_ID) " +
    "FROM USERS INNER JOIN USER_QUIZ_JUNCTIONS J ON USERS.USER_ID = J.USER_ID " +
    "WHERE USERS.USER_ROLE = 'STUDENT' AND USERS.GROUP_ID = ? AND J.QUIZ_ID = ? " +
    "AND J.STUDENT_QUIZ_STATUS = 'CLOSED';";

    private static final String FIND_RESULTS_NUMBER_BY_GROUP_ID_AND_QUIZ_ID =
    "SELECT COUNT(J.RESULT) " +
    "FROM USER_QUIZ_JUNCTIONS J INNER JOIN USERS ON J.USER_ID = USERS.USER_ID " +
    "WHERE USERS.GROUP_ID = ? AND J.QUIZ_ID = ?;";

    private static final String FIND_FINAL_RESULTS_NUMBER_BY_GROUP_ID_AND_QUIZ_ID =
    "SELECT COUNT(J.RESULT) " +
    "FROM USER_QUIZ_JUNCTIONS J INNER JOIN USERS ON J.USER_ID = USERS.USER_ID " +
    "WHERE USERS.GROUP_ID = ? AND J.QUIZ_ID = ? AND J.STUDENT_QUIZ_STATUS = 'CLOSED';";

    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD =
    "SELECT * FROM USERS WHERE LOGIN = ? AND PASSWORD = ?;";

    private static final String REGISTER_USER =
    "INSERT INTO USERS (GROUP_ID, FIRST_NAME, LAST_NAME, EMAIL, " +
    "DATE_OF_BIRTH, PHONE_NUMBER, LOGIN, PASSWORD, USER_ROLE) " +
    "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String ADD_STUDENT_TO_GROUP_BY_GROUP_ID_AND_USER_ID =
    "UPDATE USERS " +
    "SET GROUP_ID = ? " +
    "WHERE USER_ID = ? AND USER_ROLE = 'STUDENT';";

    private static final String EDIT_USER =
    "UPDATE USERS " +
    "SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, DATE_OF_BIRTH = ?, PHONE_NUMBER = ?, PASSWORD = ? " +
    "WHERE USER_ID = ?;";

    private static final String DELETE_STUDENT_FROM_GROUP_BY_USER_ID =
    "UPDATE USERS SET GROUP_ID = NULL WHERE USER_ID = ? AND USER_ROLE = 'STUDENT';";

    private static final String DELETE_STUDENTS_FROM_GROUP_BY_GROUP_ID =
    "UPDATE USERS SET GROUP_ID = NULL WHERE GROUP_ID = ? AND USER_ROLE = 'STUDENT';";
}