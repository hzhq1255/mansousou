package cn.zucc.edu.mansousou.entity.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 21:03
 */
@Data
@Entity
@Table(name = "comic_chapter")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String ChapterId;
    private String comicId;
    private String chapter;
    private Integer no;
    private String url;
    private Date createTime;
    private Date updateTime;
}
