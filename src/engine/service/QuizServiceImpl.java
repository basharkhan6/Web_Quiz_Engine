package engine.service;

import engine.model.Completion;
import engine.model.User;
import engine.repository.CompletionRepository;
import engine.repository.QuizRepository;
import engine.model.dto.Feedback;
import engine.model.Quiz;
import engine.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final CompletionRepository completionRepository;

    public QuizServiceImpl(QuizRepository quizRepository, UserRepository userRepository, CompletionRepository completionRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.completionRepository = completionRepository;
    }


    @Override
    public Quiz save(Quiz quiz, String email) {
        quiz.setAuthor(userRepository.findByEmailIgnoreCase(email));
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz find(int id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Page<Quiz> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return quizRepository.findAll(pageable);
    }

    @Override
    public void delete(int id, String email) {
        Quiz quiz = find(id);
        User user = userRepository.findByEmailIgnoreCase(email);
        if (!quiz.getAuthor().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        quizRepository.deleteById(quiz.getId());
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Feedback getSolve(Quiz quiz, int[] answer, String email) {
        if (Arrays.equals(answer, quiz.getAnswer())) {
            updateCompletions(email, quiz.getId());
            return new Feedback(true, "Congratulations, you're right!");
        } else {
            return new Feedback(false, "Wrong answer! Please, try again.");
        }

    }

    @Override
    public Page<Completion> getCompletions(String email, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, sort);
        return completionRepository.findAllByUser(userRepository.findByEmailIgnoreCase(email), pageable);
    }

    private void updateCompletions(String email, int quizId) {
        Completion completion = new Completion();
        completion.setId(quizId);
        completion.setCompletedAt(LocalDateTime.now());
        completion.setUser(userRepository.findByEmailIgnoreCase(email));
        completionRepository.save(completion);
    }

}
