package life.majiang.community.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by codedrinker on 2019/5/30.
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
