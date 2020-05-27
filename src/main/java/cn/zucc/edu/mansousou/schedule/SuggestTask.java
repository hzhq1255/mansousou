package cn.zucc.edu.mansousou.schedule;

import cn.zucc.edu.mansousou.entity.es.SuggestEs;
import cn.zucc.edu.mansousou.entity.jpa.Comic;
import cn.zucc.edu.mansousou.repository.es.SuggestEsRepository;
import cn.zucc.edu.mansousou.repository.jpa.ComicJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/27 16:13
 * @desc:
 */
@Component
public class SuggestTask {

    @Autowired
    SuggestEsRepository suggestEsRepository;

    @Autowired
    ComicJpaRepository comicJpaRepository;

//    @Scheduled(cron = "0 0 1 * * ?")
//    public void syncIndex(){
//        List<Comic> comics = comicJpaRepository.findAll();
//        for (Comic comic : comics){
//            SuggestEs suggestEs = new SuggestEs();
//            suggestEs.setId(comic.getComicId());
//            suggestEs.setSuggestText(comic.getTitle());
//            suggestEs.setFullPinyin(comic.getTitle());
//            suggestEs.setPrefixPinyin(comic.getTitle());
//            try {
//                suggestEsRepository.save(suggestEs);
//                Thread.sleep(40);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

}
