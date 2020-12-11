package engine.controller;

import engine.model.Completion;
import engine.model.Quiz;
import engine.service.QuizService;
import engine.model.dto.Feedback;
import engine.model.dto.Answer;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
public class QuizController {

    private static final String NOT_FOUND_MESSAGE = "Quiz Not Found";

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public Quiz createQuiz(@RequestBody @Valid Quiz quiz) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return quizService.save(quiz, authentication.getName());
    }

    @GetMapping(value = "/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return quizService.find(id);
    }

    @GetMapping(value = "/api/quizzes")
    public Page<Quiz> getAllQuiz(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sort) {
        return quizService.findAll(page, size, sort);
    }

    @PostMapping(value = "/api/quizzes/{id}/solve", consumes = "application/json")
    public Feedback getSolve(@PathVariable int id, @RequestBody Answer solveRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return quizService.getSolve(quizService.find(id), solveRequest.getAnswer(), authentication.getName());
    }

    @DeleteMapping("/api/quizzes/{id}")
    public void deleteQuiz(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        quizService.delete(id, authentication.getName());
    }

    @GetMapping("/api/quizzes/completed")
    public Page<Completion> getCompletions(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "completedAt") String sort) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return quizService.getCompletions(authentication.getName(), page, size, sort);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = NOT_FOUND_MESSAGE)
    public HashMap<String, String> handleIndexOutOfBoundsException(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", NOT_FOUND_MESSAGE);
        response.put("error", e.getClass().getSimpleName());
        return response;
    }


}
