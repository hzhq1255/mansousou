package cn.zucc.edu.mansousou.entity.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/25 16:10
 * @desc:
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "user_recommend")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Recommend implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Comic comic;

    @ManyToOne
    @JsonIgnore
    private User user;

    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;

}
