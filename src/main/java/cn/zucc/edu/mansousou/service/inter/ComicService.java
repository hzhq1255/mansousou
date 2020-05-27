package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.es.ComicEs;
import cn.zucc.edu.mansousou.entity.jpa.Comic;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.IOException;
import java.util.List;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 17:37
 */
public interface ComicService {
    /**
     * 通过 es 查询 漫画 匹配 title
     * @param keyword
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<ComicEs> searchComic(String keyword, Integer currentPage, Integer pageSize) throws IOException;


    /**
     * 高级搜索
     * @param comicEs
     * @param isExactMatch
     * @param currentPage
     * @param pageSize
     * @return 搜索分页结果
     */
    Page<ComicEs> advancedSearch(ComicEs comicEs,Integer isExactMatch,Integer currentPage,Integer pageSize);


    /**
     * 通过 jpa 获取所有的 漫画
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Comic> getAllComics(Integer currentPage,Integer pageSize);

    /**
     *  获取漫画详情
     * @param comicId
     * @return
     */
    Result getComicByComicId(String comicId);


    void syncIndex(List<ComicEs> comics);
}
