package cn.zucc.edu.mansousou.entity.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/22 21:10
 * @desc:
 */
@Data
@Document(indexName = "suggest",type = "doc",useServerConfiguration = true,createIndex = false)
public class SuggestEs {
    @Id
    @Field(type = FieldType.Auto)
    private String id;
    @Field(type = FieldType.Auto)
    private String suggestText;
    @Field(type = FieldType.Auto)
    private String prefixPinyin;
    @Field(type = FieldType.Auto)
    private String fullPinyin;
}
