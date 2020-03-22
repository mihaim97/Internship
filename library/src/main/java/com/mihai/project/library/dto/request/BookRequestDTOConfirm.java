package com.mihai.project.library.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class BookRequestDTOConfirm {

    @NotNull
    private int id;

    @NotBlank
    @Pattern(regexp = "AC|RJ")
    private String answerStatus;

    public BookRequestDTOConfirm() {
    }

    public BookRequestDTOConfirm(@NotNull int id, @NotBlank @Pattern(regexp = "AC|RJ") String answerStatus) {
        this.id = id;
        this.answerStatus = answerStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(String answerStatus) {
        this.answerStatus = answerStatus;
    }
}
