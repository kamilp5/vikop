package io.spring.vikop.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewArticleDto {

    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotEmpty
    @URL
    private String thumbnailUrl;
    @NotEmpty
    @URL
    private String articleUrl;
    private List<String> tags = new ArrayList<>();

}
