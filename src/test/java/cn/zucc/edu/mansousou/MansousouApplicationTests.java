package cn.zucc.edu.mansousou;

import cn.zucc.edu.mansousou.entity.es.ComicEs;
import cn.zucc.edu.mansousou.entity.es.SuggestEs;
import cn.zucc.edu.mansousou.entity.jpa.Comic;
import cn.zucc.edu.mansousou.entity.jpa.RecommendScore;
import cn.zucc.edu.mansousou.repository.es.SuggestEsRepository;
import cn.zucc.edu.mansousou.repository.jpa.ComicJpaRepository;
import cn.zucc.edu.mansousou.service.impl.RecommendServiceImpl;
import cn.zucc.edu.mansousou.service.impl.SuggestServiceImpl;
import cn.zucc.edu.mansousou.service.inter.RecommendService;
import cn.zucc.edu.mansousou.service.inter.SuggestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class MansousouApplicationTests {

    @Autowired
    SuggestEsRepository suggestEsRepository;

    @Autowired
    ComicJpaRepository comicJpaRepository;

    @Autowired
    ElasticsearchRestTemplate esTemplate;

    @Autowired
    RecommendService recommendService;

    @Test
    void contextLoads() {
    }

    @Test
    void insertSuggest(){
        List<Comic> comicList = new ArrayList<>();
        comicList = comicJpaRepository.findAll();
        List<SuggestEs> suggestEsList = new ArrayList<>();

        for (Comic comic : comicList){
            SuggestEs suggestEs = new SuggestEs();
            suggestEs.setId(comic.getComicId());
            suggestEs.setSuggestText(comic.getTitle());
            suggestEs.setPrefixPinyin(comic.getTitle());
            suggestEs.setFullPinyin(comic.getTitle());
            suggestEsList.add(suggestEs);
        }
        int size = suggestEsList.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            SuggestEs suggestEs = suggestEsList.get(i);
            try {
                suggestEsRepository.save(suggestEs);
                System.out.println(suggestEs.toString());
                System.out.println(count++);
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void bulkIndex(){
        List<Comic> comicList = new ArrayList<>();
        comicList = comicJpaRepository.findAll();
        try {
            int count = 0;
            if (!esTemplate.indexExists(SuggestEs.class)){
                esTemplate.createIndex(SuggestEs.class);
            }
            List<IndexQuery> queryList = new ArrayList<>();
            for (Comic comic : comicList){
                SuggestEs suggestEs = new SuggestEs();
                suggestEs.setId(comic.getComicId());
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(suggestEs.getId());
                indexQuery.setObject(suggestEs);
                indexQuery.setIndexName("suggest");
                indexQuery.setType("doc");
                queryList.add(indexQuery);
                if (count % 500 == 0){
                    esTemplate.bulkIndex(queryList);
                    queryList.clear();
                }
                count++;
            }
            if (queryList.size() > 0){
                esTemplate.bulkIndex(queryList);
            }
        }catch (Exception e){

        }
    }

    @Test
    void testSuggest(){
        SuggestService suggestService = new SuggestServiceImpl();
        List<String> result = suggestService.getSuggest("盘");
        System.out.println(result);
    }

    @Test
    void testRecommendScore(){
        String tags = recommendService.getUserTags(1);
        LinkedList<RecommendScore> recommendScores =
                recommendService.getAllComicScore(1);
        for (RecommendScore recommendScore : recommendScores){
            System.out.println(recommendScore.getScore());
        }
        System.out.println("test");
    }

    @Test
    void testSaveRecommend(){
        recommendService.saveRecommendUser(1);
    }

    @Test
    void testRandComic(){
        List<Comic> comics = recommendService.getRandComic(10);
        System.out.println(comics);
    }
}
