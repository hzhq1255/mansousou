package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.jpa.Comic;
import org.elasticsearch.common.util.IntArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 16:55
 */
@Repository
public interface ComicJpaRepository extends JpaRepository<Comic,String> {
    /**
     * 获取全部
     * @param pageable
     * @return
     */
    @Query("select comic from Comic comic order by comic.comicId desc ")
    Page<Comic> queryAll(Pageable pageable);

    /**
     * 查询
     * @param keyword
     * @return
     */
    @Query("select comic from Comic comic where comic.title "+
            "like concat('%',:keyword,'%') or comic.author like concat('%',:keyword,'%') "+
            "or comic.desc like concat('%',:keyword,'%')")
    List<Comic> querySearch(@Param("keyword") String keyword);

    /**
     * jpa 分页查询 单条件
     * @param title 漫画标题
     * @param pageable 分页
     * @return
     */
    Page<Comic> findAllByTitleContains(String title, Pageable pageable);

    /**
     * 收藏漫画
     * @param userId
     * @return
     */
    @Query("select comic from Comic comic,Collect collect "+
            " where comic.comicId = collect.comicId and collect.userId =:userId")
    List<Comic> findAllByUserIdAtCollect(@Param("userId") Integer userId);

    /**
     * 看过的漫画
     * @param userId
     * @return
     */
    @Query("select comic from Comic comic,Read read "+
            " where comic.comicId = read.comicId and read.userId =:userId")
    List<Comic> findAllByUserIdAtRead(@Param("userId") Integer userId);

    @Query("select comic from Comic comic" +
            " where comic.comicId not in ( " +
            "   select collect.comicId " +
            "   from Collect collect " +
            "   where collect.userId =:userId " +
            ")")
    List<Comic> findAllNotInCollect(Integer userId);

    @Query("select comic from Comic comic" +
            " where comic.comicId not in ( " +
            "   select read.comicId " +
            "   from Read read " +
            "   where read.userId =:userId " +
            ")")
    List<Comic> findAllNotInRead(Integer userId);

    @Query("select comic from Comic comic" +
            " where comic.comicId not in ( " +
            "   select collect.comicId " +
            "   from Collect collect " +
            "   where collect.userId =:userId " +
            ") and comic.comicId not in (" +
            "   select read.comicId " +
            "   from Read read " +
            "   where read.userId =:userId " +
            ")")
    List<Comic> findAllNotInReadAndCollect(Integer userId);



}
