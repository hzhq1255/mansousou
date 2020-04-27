package cn.zucc.edu.mansousou.entity.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/25 21:42
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
@Document(indexName = "comic_detail",type = "doc",useServerConfiguration = true,createIndex = false)
public class ComicEs {
    @Id
    private String id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Text,analyzer = "false")
    private String pics;
    @Field(type = FieldType.Text,analyzer = "false")
    private String url;
    @Field(type = FieldType.Text,analyzer = "false")
    private String rate;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String desc;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String author;
    @Field(type = FieldType.Text,analyzer = "false")
    private String hot;
    @Field(type = FieldType.Integer)
    private Integer collect;
    @Field(type = FieldType.Keyword)
    private String status;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String genre;
    @JsonProperty("create_time")
    @Field(type = FieldType.Date,format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd || epoch_millis")
    private Date createTime;
    @JsonProperty("update_time")
    @Field(type = FieldType.Date,format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd || epoch_millis")
    private Date updateTime;
}
