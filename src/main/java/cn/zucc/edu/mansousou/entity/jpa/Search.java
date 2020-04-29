package cn.zucc.edu.mansousou.entity.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.security.PrivateKey;
import java.util.Date;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 20:19
 */
@Entity
@Data
@Table(name = "search")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Search {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer searchId;
    @Column(name = "user_id")
    private Integer userId;
    private String keyword;
    private Date createTime;


}
