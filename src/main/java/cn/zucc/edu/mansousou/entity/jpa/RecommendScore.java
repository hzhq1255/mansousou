package cn.zucc.edu.mansousou.entity.jpa;

import lombok.Data;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/26 18:54
 * @desc:
 */
@Data
public class RecommendScore {
    private Comic comic;
    private String tags;
    private Double score;
}
