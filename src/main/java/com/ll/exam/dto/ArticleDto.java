package com.ll.exam.dto;

import lombok.*;

/**
 * ArticleDto class 역할
 * 1. 라우팅(교통정리)하기 위함.
 */

@Data
@AllArgsConstructor
public class ArticleDto {

    private long id;

    private String title;
    private String body;

}
