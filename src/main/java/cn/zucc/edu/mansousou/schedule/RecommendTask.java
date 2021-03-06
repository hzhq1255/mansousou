package cn.zucc.edu.mansousou.schedule;

import cn.zucc.edu.mansousou.entity.es.ComicEs;
import cn.zucc.edu.mansousou.entity.jpa.Chapter;
import cn.zucc.edu.mansousou.entity.jpa.Comic;
import cn.zucc.edu.mansousou.repository.jpa.ComicJpaRepository;
import cn.zucc.edu.mansousou.service.inter.ChapterService;
import cn.zucc.edu.mansousou.service.inter.ComicService;
import cn.zucc.edu.mansousou.service.inter.RecommendService;
import cn.zucc.edu.mansousou.service.inter.SuggestService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/27 15:07
 * @desc:
 */
@Component
public class RecommendTask {




    @Autowired
    RecommendService recommendService;


//    @Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    public void saveRecommend(){
        System.out.println("正在更新推荐表  "+(new Date()).toString());
        System.out.println("------------");
        recommendService.saveRecommendAllUser();
        System.out.println("更新完成  "+(new Date()).toString());

    }

    @Scheduled(cron = "0 30 13 ? * *")
    public void test(){
        System.out.println("正在测试");
    }

}
