package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.es.ChapterEs;
import cn.zucc.edu.mansousou.entity.jpa.Chapter;
import cn.zucc.edu.mansousou.repository.es.ChapterEsRepository;
import cn.zucc.edu.mansousou.repository.jpa.ChapterJpaRepository;
import cn.zucc.edu.mansousou.service.inter.ChapterService;
import cn.zucc.edu.mansousou.util.Result;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 23:23
 */
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    ChapterEsRepository chapterEsRepository;
    ChapterJpaRepository chapterJpaRepository;

    @Autowired
    public void setChapterEsRepository(ChapterEsRepository chapterEsRepository) {
        this.chapterEsRepository = chapterEsRepository;
    }

    @Autowired
    public void setChapterJpaRepository(ChapterJpaRepository chapterJpaRepository) {
        this.chapterJpaRepository = chapterJpaRepository;
    }

    @Override
    public Page<ChapterEs> getAllChapterByComicId(String comicId, Integer currentPage,Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.must(QueryBuilders.matchPhraseQuery("comic_id",comicId));
        FieldSortBuilder sortBuilder = SortBuilders.fieldSort("no").order(SortOrder.DESC);
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withSort(sortBuilder)
                .withPageable(pageable)
                .build();
        return chapterEsRepository.search(query);
    }

    @Override
    public Page<Chapter> getAllChapter(Integer currentPage, Integer pageSize) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return chapterJpaRepository.selectAll(pageable);
    }

    @Override
    public Result getAllChapterByComicId(String comicId) {
        return null;
    }

}
