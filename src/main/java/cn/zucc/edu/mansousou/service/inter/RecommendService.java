package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.jpa.Comic;
import cn.zucc.edu.mansousou.entity.jpa.Recommend;
import cn.zucc.edu.mansousou.entity.jpa.RecommendScore;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/25 20:18
 * @desc:
 */
public interface RecommendService {


    List<Recommend> getRecommendByUser(Integer userId);

    String getCollectTags(Integer userId);

    String getReadTags(Integer userId);

    String getUserTags(Integer userId);

    LinkedList<RecommendScore> getAllComicScore(Integer userId);

    String getComicTags(String comicId);

    Integer saveRecommendUser(Integer userId);

    Integer saveRecommendAllUser();

    List<Comic> getRandComic(Integer limit);
}
