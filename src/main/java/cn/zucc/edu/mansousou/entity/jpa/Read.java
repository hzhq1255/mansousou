package cn.zucc.edu.mansousou.entity.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 20:20
 */
@Data
@Entity
@Table(name = "user_read")
public class Read {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer readId;
    private Integer userId;
    private String chapterId;
    private String chapter;
    private String url;
    private Date createTime;
    private Date updateTime;
}
