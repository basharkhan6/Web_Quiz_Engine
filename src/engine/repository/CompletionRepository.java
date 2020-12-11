package engine.repository;

import engine.model.Completion;
import engine.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletionRepository extends JpaRepository<Completion, Integer> {
    Page<Completion> findAllByUser(User user, Pageable pageable);
}
