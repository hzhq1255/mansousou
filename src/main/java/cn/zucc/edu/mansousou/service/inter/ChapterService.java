package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.es.ChapterEs;
import cn.zucc.edu.mansousou.entity.jpa.Chapter;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.data.domain.Page;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 23:02
 */
public interface ChapterService {

    /**
     * 通过 es 来查询漫画的章节信息
     * @param comicId
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<ChapterEs> getAllChapterByComicId(String comicId, Integer currentPage, Integer pageSize);

    /**
     * 获取所有章节
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Chapter> getAllChapter(Integer currentPage, Integer pageSize);

    /**
     * es 通过 id 获取 漫画详情
     * @param comicId
     * @return
     */
    Result getAllChapterByComicId(String comicId);
}
