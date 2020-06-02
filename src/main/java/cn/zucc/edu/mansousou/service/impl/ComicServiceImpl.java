package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.es.ComicEs;
import cn.zucc.edu.mansousou.entity.es.HighlightResultMapper;
import cn.zucc.edu.mansousou.entity.jpa.Comic;
import cn.zucc.edu.mansousou.repository.es.ComicEsRepository;
import cn.zucc.edu.mansousou.repository.jpa.ComicJpaRepository;
import cn.zucc.edu.mansousou.service.inter.ComicService;
import cn.zucc.edu.mansousou.util.Result;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;

/**
 * @author hzhq1255
 * @mail hzhq1255@163.com
 * @Date: 2020/4/27 16:44
 */
@Service
public class ComicServiceImpl implements ComicService {

    ComicEsRepository comicEsRepository;

    ComicJpaRepository comicJpaRepository;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    public void setComicEsRepository(ComicEsRepository comicEsRepository) {
        this.comicEsRepository = comicEsRepository;
    }

    @Autowired
    public void setComicJpaRepository(ComicJpaRepository comicJpaRepository) {
        this.comicJpaRepository = comicJpaRepository;
    }

    @Override
    public Page<ComicEs> searchComic(String keyword, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.should(QueryBuilders.matchPhraseQuery("title",keyword).boost(100f).analyzer("ik_max_word"));
        builder.should(QueryBuilders.matchPhraseQuery("author",keyword).boost(10f).analyzer("ik_max_word"));
        builder.should(QueryBuilders.matchPhraseQuery("desc",keyword).boost(1f).analyzer("ik_max_word"));
        String preTags = "<font color=\"blue\">";
        String postTags = "</font>";
        HighlightBuilder.Field highlightField1 = new HighlightBuilder.Field("title")
                .requireFieldMatch(false)
                .preTags(preTags)
                .postTags(postTags);
        HighlightBuilder.Field highlightField2 = new HighlightBuilder.Field("author")
                .requireFieldMatch(false)
                .preTags(preTags)
                .postTags(postTags);
        HighlightBuilder.Field highlightField3 = new HighlightBuilder.Field("desc")
                .requireFieldMatch(false)
                .preTags(preTags)
                .postTags(postTags);
        HighlightBuilder.Field[] fields = new HighlightBuilder.Field[]{highlightField1,highlightField2,highlightField3};
//        HighlightBuilder highlightBuilder1 = new HighlightBuilder()
//                .field("title")
//                .preTags("<span style=\"color:blue;font-weight:bold;font-size:15px;\">")
//                .postTags("</span>");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withHighlightFields(fields)
                .withPageable(pageable)
                .build();

        //Page<Object> objectPage = elasticsearchRestTemplate.queryForPage(searchQuery,Object.class);
        Page<ComicEs> comicEsPage = elasticsearchRestTemplate.queryForPage(searchQuery,ComicEs.class,new HighlightResultMapper());
        return comicEsPage;
    }

    @Override
    public Page<ComicEs> advancedSearch(ComicEs comicEs,Integer isExactMatch, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (isExactMatch == null || isExactMatch == 0 ){
            if (comicEs.getTitle() != null && !"".equals(comicEs.getTitle())){
                builder.should(QueryBuilders.matchQuery("title",comicEs.getTitle()).boost(10f));
            }
            if (comicEs.getAuthor() != null && !"".equals(comicEs.getAuthor())){
                builder.should(QueryBuilders.matchQuery("author",comicEs.getAuthor()).boost(8f));
            }
            if (comicEs.getDesc() != null && !"".equals(comicEs.getDesc())){
                builder.should(QueryBuilders.matchQuery("desc",comicEs.getDesc()).boost(3f));
            }
            if (comicEs.getGenre() != null && !"".equals(comicEs.getGenre())){
                builder.should(QueryBuilders.matchPhraseQuery("genre.keyword",comicEs.getGenre()).boost(4f));
            }
            if (comicEs.getStatus() != null && !"".equals(comicEs.getStatus())){
                builder.should(QueryBuilders.matchPhraseQuery("status.keyword",comicEs.getStatus()).boost(5f));
            }
        }
        if (isExactMatch != null && isExactMatch == 1){
            if (comicEs.getTitle() != null && !"".equals(comicEs.getTitle())){
                builder.must(QueryBuilders.termQuery("title.keyword",comicEs.getTitle()));
            }
            if (comicEs.getAuthor() != null && !"".equals(comicEs.getAuthor())){
                builder.must(QueryBuilders.termQuery("author.keyword",comicEs.getAuthor()));
            }
            if (comicEs.getDesc() != null && !"".equals(comicEs.getDesc())){
                builder.must(QueryBuilders.termQuery("desc.keyword",comicEs.getDesc()));
            }
            if (comicEs.getGenre() != null && !"".equals(comicEs.getGenre())){
                builder.must(QueryBuilders.termQuery("genre.keyword",comicEs.getGenre()));
            }
            if (comicEs.getStatus() != null && !"".equals(comicEs.getStatus())){
                builder.must(QueryBuilders.termQuery("status.keyword",comicEs.getStatus()));
            }
        }
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .field("title");
        return comicEsRepository.search(builder,pageable) ;
    }


    @Override
    public Page<Comic> getAllComics(Integer currentPage,Integer pageSize){
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return comicJpaRepository.queryAll(pageable);
    }

    @Override
    public Result getComicByComicId(String comicId) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.must(QueryBuilders.termQuery("id",comicId));
        Page<ComicEs> comicEsPage = (Page<ComicEs>) comicEsRepository.search(builder);
        ComicEs comicEs = comicEsPage.getContent().get(0);
        if (comicEs == null){
            return Result.success("没有此漫画");
        }
        return Result.success(comicEs);
    }

    @Override
    public void syncIndex(List<ComicEs> comics) {
        for (ComicEs comic : comics){
            try {
                comicEsRepository.save(comic);
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
