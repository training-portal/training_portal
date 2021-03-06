package com.studysoft.trainingportal.validator;

import com.studysoft.trainingportal.model.Quiz;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class QuizValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Quiz.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Quiz quiz = (Quiz) target;
        validateQuizName(quiz.getName(), errors);
        validateDescription(quiz.getDescription(), errors);
        validateExplanation(quiz.getExplanation(), errors);
    }

    public void validateQuizName(String name, Errors errors) {
        if (name == null || name.isEmpty()) {
            errors.rejectValue("name", "validation.quiz.name.empty");
        } else if (name.length() > 50) {
            errors.rejectValue("name", "validation.quiz.name.size");
        }
    }

    public void validateDescription(String description, Errors errors) {
        if (description == null) {
            return;
        } else if (description.length() > 65535) {
            errors.rejectValue("description", "validation.quiz.description.size");
        }
    }

    public void validatePassingTime(String hours, String minutes, String seconds, Errors errors) {
        if (hours != null && minutes != null && seconds != null) {
            if (hours.isEmpty() && minutes.isEmpty() && seconds.isEmpty()) {
                errors.rejectValue("passingTime", "validation.quiz.passing.time.empty");
                return;
            }

            if (!hours.isEmpty() && !hours.matches("[0-9]+")) {
                errors.rejectValue("passingTime", "validation.quiz.hours.format");
            }

            if (!minutes.isEmpty()) {
                if (!minutes.matches("[0-9]+")) {
                    errors.rejectValue("passingTime", "validation.quiz.minutes.format");
                } else if (Integer.valueOf(minutes) < 0 || Integer.valueOf(minutes) > 60) {
                    errors.rejectValue("passingTime", "validation.quiz.minutes.size");
                }
            }

            if (!seconds.isEmpty()) {
                if (!seconds.matches("[0-9]+")) {
                    errors.rejectValue("passingTime", "validation.quiz.seconds.format");
                } else if (Integer.valueOf(seconds) < 0 || Integer.valueOf(seconds) > 60) {
                    errors.rejectValue("passingTime", "validation.quiz.seconds.size");
                }
            }
        }
    }

    public void validateExplanation(String explanation, Errors errors) {
        if (explanation == null) {
            return;
        } else if (explanation.length() > 65535) {
            errors.rejectValue("explanation", "validation.quiz.explanation.size");
        }
    }
}
