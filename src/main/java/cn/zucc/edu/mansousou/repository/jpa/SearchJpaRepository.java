package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.jpa.HotSearch;
import cn.zucc.edu.mansousou.entity.jpa.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    Page<HotSearch> selectTotalHotSearch(Pageable pageable);

    /**
     * 获取所有搜索内容
     * @param pageable
     * @return
     */
    @Query("select s from Search s order by s.createTime desc ")
    Page<Search> selectAllSearch(Pageable pageable);
}
