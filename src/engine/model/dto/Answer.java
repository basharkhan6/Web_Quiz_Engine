package engine.model.dto;

import java.util.Arrays;

public class Answer {
    private int[] answer;

    public Answer() {
    }

    public Answer(int[] answer) {
        this.answer = answer;
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
