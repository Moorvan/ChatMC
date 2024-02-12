package dev.morvan.model;

import lombok.Data;

@Data
public class TriagedReview {
    Evaluation evaluation;
    String message;

    public enum Evaluation {
        POSITIVE, NEGATIVE
    }
}
