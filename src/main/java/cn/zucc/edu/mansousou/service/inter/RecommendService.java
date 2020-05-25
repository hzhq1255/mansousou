package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.jpa.Recommend;

import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/25 20:18
 * @desc:
 */
public interface RecommendService {


    List<Recommend> getRecommendByUser(Integer userId);
}
