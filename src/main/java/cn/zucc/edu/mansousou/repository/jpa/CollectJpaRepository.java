package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.jpa.Collect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 20:24
 */
public interface CollectJpaRepository extends JpaRepository<Collect,Integer> {

    /**
     * 通过用户 id 得到收藏记录
     * @param userId
     * @param pageable
     * @return
     */
    @Query("select collect from Collect collect where collect.userId =:userId order by collect.updateTime desc ")
    Page<Collect> selectAllByUserId(@Param("userId") Integer userId, Pageable pageable);

    /**
     * 通过用户 名字 得到收藏记录
     * @param userName
     * @param pageable
     * @return
     */
    @Query("select collect from Collect collect where collect.userId =:userName order by collect.updateTime desc ")
    Page<Collect> selectAllByUserName(@Param("userName") String userName, Pageable pageable);

    /**
     * 获取所有订阅
     * @param pageable
     * @return
     */
    @Query("select collect from Collect collect order by collect.updateTime desc ")
    Page<Collect> selectAll(Pageable pageable);

    /**
     * 订阅修改
     * @param collectId
     * @param comicId
     * @param title
     * @param url
     * @param updateTime
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    @Query("update Collect collect set collect.comicId =:comicId, " +
            "collect.title =:title, collect.url =:url, collect.updateTime =:updateTime " +
            "where collect.collectId =:collectId")
    Integer updateCollect(@Param("collectId") Integer collectId,
                          @Param("comicId") String comicId,
                          @Param("title") String title,
                          @Param("url") String url,
                          @Param("updateTime")Date updateTime);

    /**
     * 删除收藏
     * @param collectId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    Integer deleteByCollectId(Integer collectId);

    /**
     * 删除用户收藏
     * @param userId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    List<Collect> deleteAllByUserId(Integer userId);
}
