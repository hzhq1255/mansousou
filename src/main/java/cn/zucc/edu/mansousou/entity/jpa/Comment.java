package cn.zucc.edu.mansousou.entity.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;


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

}
