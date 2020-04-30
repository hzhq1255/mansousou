package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.jpa.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 22:50
 */
@Repository
public interface ChapterJpaRepository extends JpaRepository<Chapter,String> {
    /**
     * 查询漫画的章节
     * @param comicId
     * @param pageable
     * @return
     */
    @Query("select chapter from Chapter chapter where chapter.comicId =:comicId order by chapter.createTime desc ")
    Page<Chapter> selectAllByComicId(String comicId, Pageable pageable);

    /**
     * 获取所有章节
     * @param pageable
     * @return
     */
    @Query("select chapter from Chapter chapter order by chapter.updateTime desc ")
    Page<Chapter> selectAll(Pageable pageable);
}
