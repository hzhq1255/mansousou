package cn.zucc.edu.mansousou.entity.jpa;

import cn.zucc.edu.mansousou.entity.Dto.ComtLike;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "user_comment")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer commentId;

    private Integer userId;

    private String comicId;

    @Column(name = "[desc]")
    private String desc;

    private Date updateTime;

    private Integer likenum;


    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<ComtLike> comtLikeList;

    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<Reply> replyList;

}
