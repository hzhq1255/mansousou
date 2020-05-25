package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.jpa.Recommend;
import cn.zucc.edu.mansousou.repository.jpa.ComicJpaRepository;
import cn.zucc.edu.mansousou.repository.jpa.RecommendJpaRepository;
import cn.zucc.edu.mansousou.repository.jpa.UserJpaRepository;
import cn.zucc.edu.mansousou.service.inter.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/25 20:19
 * @desc:
 */
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    ComicJpaRepository comicJpaRepository;

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    RecommendJpaRepository recommendJpaRepository;

    @Override
    public List<Recommend> getRecommendByUser(Integer userId) {



        return null;
    }
}
