package cn.zucc.edu.mansousou.entity.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Integer id;
    private String name;

}
