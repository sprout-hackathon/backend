package com.hackathon.sprout.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
    private String title;
    private String content;
    private String category;
    private Boolean isAnonymous;
}