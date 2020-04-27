package cn.zucc.edu.mansousou.entity.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 20:19
 */
@Data
@Entity
@Table(name = "user_collect")
public class Collect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer collectId;
    private Integer userId;
    private String comicId;
    private String title;
    private String url;
    private Date createTime;
    private Date updateTime;
}
