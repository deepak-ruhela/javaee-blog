package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private int id;
    private int postId;
    private int userId;
    private String content;
    private String time;

}
