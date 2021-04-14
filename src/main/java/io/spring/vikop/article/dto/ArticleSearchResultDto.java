package io.spring.vikop.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSearchResultDto {

    private Long id;
    private String title;
    private String thumbnailUrl;
}
