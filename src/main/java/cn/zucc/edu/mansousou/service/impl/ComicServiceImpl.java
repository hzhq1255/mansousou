package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.es.ComicEs;
import cn.zucc.edu.mansousou.entity.jpa.Comic;
import cn.zucc.edu.mansousou.repository.es.ComicEsRepository;
import cn.zucc.edu.mansousou.repository.jpa.ComicJpaRepository;
import cn.zucc.edu.mansousou.service.inter.ComicService;
import cn.zucc.edu.mansousou.util.Result;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
        builder.should(QueryBuilders.matchPhraseQuery("title",keyword).boost(5f));
        builder.should(QueryBuilders.matchPhraseQuery("desc",keyword).boost(1f));
        return comicEsRepository.search(builder,pageable);
    }

    @Override
    public Page<ComicEs> advancedSearch(ComicEs comicEs,Integer isExactMatch, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (isExactMatch == null || isExactMatch == 0 ){
            if (comicEs.getTitle() != null && !"".equals(comicEs.getTitle())){
                builder.should(QueryBuilders.matchPhraseQuery("title",comicEs.getTitle()));
            }
            if (comicEs.getAuthor() != null && !"".equals(comicEs.getAuthor())){
                builder.should(QueryBuilders.matchPhraseQuery("author",comicEs.getAuthor()));
            }
            if (comicEs.getDesc() != null && !"".equals(comicEs.getDesc())){
                builder.should(QueryBuilders.matchPhraseQuery("desc",comicEs.getDesc()));
            }
            if (comicEs.getGenre() != null && !"".equals(comicEs.getGenre())){
                builder.should(QueryBuilders.matchPhraseQuery("genre",comicEs.getGenre()));
            }
            if (comicEs.getStatus() != null && !"".equals(comicEs.getStatus())){
                builder.should(QueryBuilders.matchPhraseQuery("status",comicEs.getStatus()));
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
}
