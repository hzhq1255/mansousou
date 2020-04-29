package cn.zucc.edu.mansousou.entity.jpa;

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
public class Search {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer searchId;
    private Integer userId;
    private String keyword;
    private Date createTime;

    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
