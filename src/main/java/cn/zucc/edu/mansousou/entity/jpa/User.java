package cn.zucc.edu.mansousou.entity.jpa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 17:24
 */
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @JsonProperty("id")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @JsonProperty("name")
    @Column(name = "name")
    private String userName;
    private String password;
    private Date createTime;
    private Date updateTime;
}
