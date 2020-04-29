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
    private String content;
    private Integer count;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
