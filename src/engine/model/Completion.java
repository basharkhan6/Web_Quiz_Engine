package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Completion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int completionId;
    @NotNull
    private int id;
    @NotNull
    @PastOrPresent
    private LocalDateTime completedAt;
    @NotNull
    @ManyToOne
    @JsonIgnore
    private User user;

    public Completion() {
    }

    public Completion(int completionId, @NotNull int id, @NotNull @PastOrPresent LocalDateTime completedAt, @NotNull User user) {
        this.completionId = completionId;
        this.id = id;
        this.completedAt = completedAt;
        this.user = user;
    }

    public int getCompletionId() {
        return completionId;
    }

    public void setCompletionId(int completionId) {
        this.completionId = completionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
