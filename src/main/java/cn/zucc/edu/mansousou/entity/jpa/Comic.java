package cn.zucc.edu.mansousou.entity.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 16:54
 *
 * CREATE TABLE `comic_detail` (
 *   `id` varchar(100) NOT NULL,
 *   `title` varchar(100) DEFAULT NULL,
 *   `pics` varchar(400) DEFAULT NULL,
 *   `url` varchar(400) DEFAULT NULL,
 *   `rate` varchar(45) DEFAULT NULL,
 *   `desc` longtext,
 *   `author` varchar(45) DEFAULT NULL,
 *   `hot` varchar(45) DEFAULT NULL,
 *   `collect` int(11) DEFAULT NULL,
 *   `status` varchar(45) DEFAULT NULL,
 *   `genre` varchar(200) DEFAULT NULL,
 *   `create_time` timestamp NULL DEFAULT NULL,
 *   `update_time` timestamp NULL DEFAULT NULL,
 *   PRIMARY KEY (`id`),
 *   UNIQUE KEY `id_UNIQUE` (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
@Data
@Table(name = "comic_detail")
@Entity
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    private String pics;
    private String url;
    private String rate;
    @Column(columnDefinition = "longtext")
    private String desc;
    private String author;
    private String hot;
    private Integer collect;
    private String status;
    private String genre;
    private Date createTime;
    private Date updateTime;
}
