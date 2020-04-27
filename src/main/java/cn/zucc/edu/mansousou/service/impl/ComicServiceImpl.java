package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.es.ComicEs;
import cn.zucc.edu.mansousou.entity.jpa.Comic;
import cn.zucc.edu.mansousou.repository.es.ComicEsRepository;
import cn.zucc.edu.mansousou.repository.jpa.ComicJpaRepository;
import cn.zucc.edu.mansousou.service.inter.ComicService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<ComicEs> searchComic(String keyword, Integer currentPage, Integer pageSize){
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.should(QueryBuilders.matchPhraseQuery("title",keyword));
        return comicEsRepository.search(builder,pageable);
    }

    @Override
    public Page<Comic> getAllComics(Integer currentPage,Integer pageSize){
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return comicJpaRepository.queryAll(pageable);
    }
}
