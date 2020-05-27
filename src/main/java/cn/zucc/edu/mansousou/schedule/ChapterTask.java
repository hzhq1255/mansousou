package cn.zucc.edu.mansousou.schedule;

import cn.zucc.edu.mansousou.entity.es.ChapterEs;
import cn.zucc.edu.mansousou.entity.jpa.Chapter;
import cn.zucc.edu.mansousou.repository.es.ChapterEsRepository;
import cn.zucc.edu.mansousou.repository.jpa.ChapterJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/27 16:20
 * @desc:
 */
@Component
public class ChapterTask {

    @Autowired
    ChapterEsRepository chapterEsRepository;

    @Autowired
    ChapterJpaRepository chapterJpaRepository;

/*    @Scheduled(cron = "0 30 1 * * ?")
    public void syncIndex(){
        List<Chapter> chapters = chapterJpaRepository.findAll();
        for (Chapter chapter : chapters){
            ChapterEs chapterEs = new ChapterEs();
            chapterEs.setChapterId(chapter.getChapterId());
            chapterEs.setChapter(chapter.getChapter());
            chapterEs.setComicId(chapter.getComicId());
            chapterEs.setNo(chapter.getNo());
            chapterEs.setUrl(chapter.getUrl());
            chapterEs.setCreateTime(chapter.getCreateTime());
            chapterEs.setUpdateTime(chapter.getUpdateTime());
            try {
                chapterEsRepository.save(chapterEs);
                Thread.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }*/


}
