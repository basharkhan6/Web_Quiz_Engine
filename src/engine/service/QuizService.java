package engine.service;

import engine.model.Completion;
import engine.model.Quiz;
import engine.model.dto.Feedback;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuizService {
    Quiz save(Quiz quiz, String email);

    Quiz find(int id);

    List<Quiz> findAll();

    Page<Quiz> findAll(int page, int size, String sort);

    void delete(int id, String email);

    Feedback getSolve(Quiz quiz, int[] answer, String email);

    Page<Completion> getCompletions(String email, int page, int size, String sort);
}
