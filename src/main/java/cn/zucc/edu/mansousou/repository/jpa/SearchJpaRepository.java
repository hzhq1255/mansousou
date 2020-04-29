package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.jpa.HotSearch;
import cn.zucc.edu.mansousou.entity.jpa.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/4/28 22:17
 * @description:
 */
public interface SearchJpaRepository extends JpaRepository<Search,Integer> {

    /**
     * 获取搜索量最高的词
     * @param pageable
     * @return
     */
    @Query("select s.content,count(s.content) from Search s order by count(s.content) desc ")
    Page<HotSearch> selectHotSearch(Pageable pageable);

    /**
     * 获取所有搜索内容
     * @param pageable
     * @return
     */
    @Query("select s from Search s order by s.createTime desc ")
    Page<Search> selectAllSearch(Pageable pageable);

    /**
     * 获取用户的搜索记录
     * @param userId
     * @param pageable
     * @return
     */
    @Query("select s from Search s where s.userId =:userId order by s.createTime desc ")
    Page<Search> selectAllSearchByUserId(@Param("userId") Integer userId,Pageable pageable);

    /**
     * 用户删除某一搜索历史
     * @param searchId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    @Query("update Search s set s.userId = null where s.searchId =:searchId")
    Integer clearBySearchId(@Param("searchId") Integer searchId);

    /**
     * 用户清空所有搜索历史
     * @param userId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    @Query("update Search s set s.userId = null where s.userId =:userId")
    Integer clearAllByUserId(@Param("userId") Integer userId);

}
