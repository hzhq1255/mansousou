package cn.zucc.edu.mansousou.entity.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.util.Date;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 21:03
 */
@Data
@Document(indexName = "comic_chapter",type = "doc",useServerConfiguration = true,createIndex = false)
public class ChapterEs {


    @Id
    @JsonProperty("id")
    private String chapterId;
    @Field(type = FieldType.Text, analyzer = "false")
    @JsonProperty("comic_id")
    private String comicId;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String chapter;
    @Field(type = FieldType.Integer, analyzer = "false")
    private Integer no;
    @Field(type = FieldType.Text, analyzer = "false")
    private String url;
    @JsonProperty("create_time")
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd || epoch_millis")
    private Date createTime;
    @JsonProperty("update_time")
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd || epoch_millis")
    private Date updateTime;

}