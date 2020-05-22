package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.es.ComicEs;
import cn.zucc.edu.mansousou.entity.jpa.HotSearch;
import cn.zucc.edu.mansousou.entity.jpa.Search;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.data.domain.Page;

import java.util.Collection;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/4/29 11:07
 * @description:
 */
public interface SearchService {

    /**
     * 搜索热点
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<HotSearch> getHotSearch(Integer currentPage, Integer pageSize);

    /**
     * 获取所有搜索内容
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Search> getAllSearch(Integer currentPage,Integer pageSize);

    /**
     * 获取用户的所有搜索内容
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<HotSearch> getAllSearchByUserId(Integer userId, Integer currentPage, Integer pageSize);


    /**
     * 删除某一搜索历史
     * @param searchId
     * @return
     */
    Result clearBySearchId(Integer searchId);

    /**
     * 用户清空所有搜索历史
     * @param userId
     * @return
     */
    Result clearAllSearchByUserId(Integer userId);

    /**
     * 高级搜索
     * @param comicEs
     * @return
     */
    Page<ComicEs> advancedSearchComic(ComicEs comicEs);

    /**
     * 添加搜索记录
     * @param search
     * @return
     */
    Result addSearch(Search search);

}
