package com.company.training_portal.model;

import com.company.training_portal.model.enums.QuestionType;

public class Question {

    private Long questionId;
    private Long quizId;
    private String name;
    private String body;
    private String explanation;
    private QuestionType questionType;
    private Integer score;
    private Integer serialNumber;

    public Question() {
    }

    private Question(QuestionBuilder builder) {
        this.questionId = builder.questionId;
        this.quizId = builder.quizId;
        this.name = builder.name;
        this.body = builder.body;
        this.explanation = builder.explanation;
        this.questionType = builder.questionType;
        this.score = builder.score;
        this.serialNumber = builder.serialNumber;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (!questionId.equals(question.questionId)) return false;
        if (!quizId.equals(question.quizId)) return false;
        if (!name.equals(question.name)) return false;
        if (!body.equals(question.body)) return false;
        if (explanation != null ? !explanation.equals(question.explanation) : question.explanation != null)
            return false;
        if (questionType != question.questionType) return false;
        if (!score.equals(question.score)) return false;
        return serialNumber.equals(question.serialNumber);
    }

    @Override
    public int hashCode() {
        int result = questionId.hashCode();
        result = 31 * result + quizId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + body.hashCode();
        result = 31 * result + (explanation != null ? explanation.hashCode() : 0);
        result = 31 * result + questionType.hashCode();
        result = 31 * result + score.hashCode();
        result = 31 * result + serialNumber.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", quizId=" + quizId +
                ", name='" + name + '\'' +
                ", body='" + body + '\'' +
                ", explanation='" + explanation + '\'' +
                ", questionType=" + questionType +
                ", score=" + score +
                ", serialNumber=" + serialNumber +
                '}';
    }

    public static final class QuestionBuilder {

        private Long questionId;
        private Long quizId;
        private String name;
        private String body;
        private String explanation;
        private QuestionType questionType;
        private Integer score;
        private Integer serialNumber;

        public QuestionBuilder() {
        }

        public QuestionBuilder questionId(Long questionId) {
            this.questionId = questionId;
            return this;
        }

        public QuestionBuilder quizId(Long quizId) {
            this.quizId = quizId;
            return this;
        }

        public QuestionBuilder name(String name) {
            this.name = name;
            return this;
        }

        public QuestionBuilder body(String body) {
            this.body = body;
            return this;
        }

        public QuestionBuilder explanation(String explanation) {
            this.explanation = explanation;
            return this;
        }

        public QuestionBuilder questionType(QuestionType questionType) {
            this.questionType = questionType;
            return this;
        }

        public QuestionBuilder score(Integer score) {
            this.score = score;
            return this;
        }

        public QuestionBuilder serialNumber(Integer serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }
}
