package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.jpa.Comic;
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
public interface ComicJpaRepository extends JpaRepository<Comic,String> {
    /**
     * 获取全部
     * @return
     */
    @Query("select comic from Comic comic order by comic.id desc ")
    Page<Comic> queryAll(Pageable pageable);

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
}
