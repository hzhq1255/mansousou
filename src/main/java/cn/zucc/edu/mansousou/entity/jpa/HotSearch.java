package cn.zucc.edu.mansousou.entity.jpa;

import lombok.Data;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/4/28 22:32
 * @description:
 */
@Data
public class HotSearch {
    private String keyword;
    private Integer count;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
