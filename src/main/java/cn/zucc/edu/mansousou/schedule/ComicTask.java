package cn.zucc.edu.mansousou.schedule;

import cn.zucc.edu.mansousou.entity.es.ComicEs;
import cn.zucc.edu.mansousou.entity.jpa.Comic;
import cn.zucc.edu.mansousou.repository.es.ComicEsRepository;
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
 * @date: 2020/5/27 15:40
 * @desc:
 */
@Component
public class ComicTask {

    @Autowired
    ComicJpaRepository comicJpaRepository;

    @Autowired
    ComicEsRepository comicEsRepository;

/*    @Scheduled(cron = "0 30 0 * * ?")
    public void syncIndex(){
        List<Comic> comics = comicJpaRepository.findAll();
        for (Comic comic : comics){
            ComicEs comicEs = new ComicEs();
            comicEs.setComicId(comic.getComicId());
            comicEs.setTitle(comic.getTitle());
            comicEs.setPics(comic.getPics());
            comicEs.setUrl(comic.getUrl());
            comicEs.setRate(comic.getRate());
            comicEs.setDesc(comic.getDesc());
            comicEs.setAuthor(comic.getAuthor());
            comicEs.setHot(comic.getHot());
            comicEs.setCollect(comic.getCollect());
            comicEs.setStatus(comic.getStatus());
            comicEs.setGenre(comic.getGenre());
            comicEs.setCreateTime(comic.getCreateTime());
            comicEs.setUpdateTime(comic.getUpdateTime());
            try {
                comicEsRepository.save(comicEs);
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}
