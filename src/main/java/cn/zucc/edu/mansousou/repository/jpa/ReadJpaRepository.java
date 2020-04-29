package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.jpa.Read;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 23:02
 */
public interface ReadJpaRepository extends JpaRepository<Read,Integer> {

    /**
     * 通过用户 id 获取所有的浏览记录
     * @param userId
     * @param pageable
     * @return
     */
    @Query("select read from Read read where read.userId =:userId order by read.updateTime desc ")
    Page<Read> selectAllByUserId(Integer userId, Pageable pageable);

    /**
     * 获取所有用户浏览信息
     * @param pageable
     * @return
     */
    @Query("select read from Read read order by read.updateTime desc ")
    Page<Read> selectAll(Pageable pageable);

    /**
     * 通过 readId 删除用户的浏览信息
     * @param readId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    Read deleteByReadId(Integer readId);

    /**
     * 删除某一用户下的所有浏览记录
     * @param userId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    List<Read> deleteAllByUserId(Integer userId);

    /**
     * 修改浏览历史
     * jpa 中 删除和修改需要增加 @Modifying 注解
     * @param readId
     * @param chapterId
     * @param chapter
     * @param url
     * @param updateTime
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    @Query("update Read read set read.chapterId =:chapterId , read.chapter =:chapter, " +
            "read.url =:url , read.updateTime =:updateTime where read.readId =:readId")
    Integer updateRead(@Param("readId") Integer readId, @Param("chapterId") String chapterId,
                      @Param("chapter") String chapter, @Param("url") String url,
                      @Param("updateTime")Date updateTime);


    /**
     * 只是修改浏览时间
     * @param userId
     * @param updateTime
     * @param comicId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    @Query("update Read read set read.updateTime =:updateTime "+
            " where read.userId =:userId and read.comicId =:comicId")
    Integer updateReadTime(@Param("userId") Integer userId,
                        @Param("comicId") String comicId,
                        @Param("updateTime")Date updateTime);

    /**
     * 通过 userId 和 comicId 查询
     * @param userId
     * @param comicId
     * @return
     */
    Read findByUserIdAndComicId(Integer userId,String comicId);
}
