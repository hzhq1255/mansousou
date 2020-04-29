package cn.zucc.edu.mansousou.entity.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date 2020/4/27 20:20
 */

@Data
@Entity
@Table(name = "user_read")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Read {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer readId;
    @Column(name = "user_id")
    private Integer userId;
    private String chapterId;
    private String chapter;
    private String comicId;
    private String title;
    private String url;
    private String pics;
    private Date createTime;
    private Date updateTime;





}
