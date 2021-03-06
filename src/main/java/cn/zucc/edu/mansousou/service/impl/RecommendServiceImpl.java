package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.jpa.Comic;
import cn.zucc.edu.mansousou.entity.jpa.Recommend;
import cn.zucc.edu.mansousou.entity.jpa.RecommendScore;
import cn.zucc.edu.mansousou.entity.jpa.User;
import cn.zucc.edu.mansousou.repository.jpa.ComicJpaRepository;
import cn.zucc.edu.mansousou.repository.jpa.RecommendJpaRepository;
import cn.zucc.edu.mansousou.repository.jpa.UserJpaRepository;
import cn.zucc.edu.mansousou.service.inter.RecommendService;
import cn.zucc.edu.mansousou.util.VectorMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/5/25 20:19
 * @desc:
 */
@Service
@Transactional
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    ComicJpaRepository comicJpaRepository;

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    RecommendJpaRepository recommendJpaRepository;

    Logger log = LoggerFactory.getLogger(RecommendServiceImpl.class);


    public String getTags(Comic comic){
        String tags = "";
        String rate = comic.getRate();
        if (!"无评分".equals(rate)){
            Pattern pattern = Pattern.compile("^[0-9]");
            Matcher matcher = pattern.matcher(rate);
            while (matcher.find()){
                rate = matcher.group(0);
            }
        }
        String hot = comic.getHot();
        if (!"".equals(hot) && hot != null){
            Pattern pattern = Pattern.compile("[亿万千百十]");
            Matcher matcher = pattern.matcher(hot);
            while (matcher.find()){
                hot = matcher.group(0);
            }
        }
        tags = comic.getGenre() + "," + comic.getAuthor() + "," + rate +
                "," + hot + "," + comic.getStatus() + ",";
        return tags;
    }

    public Boolean isExistedInScore(LinkedList<RecommendScore> recommendScores,Comic comic){
        for (RecommendScore score: recommendScores){
            if (score.getComic().getComicId() == comic.getComicId()){
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer saveRecommendUser(Integer userId) {
        String tags = this.getUserTags(userId);
        List<RecommendScore> recommendScores = this.getAllComicScore(userId).subList(0,10);
        User user = new User();
        user.setUserId(userId);
        List<Recommend> recommends = recommendJpaRepository.findAllByUser(user);
        int total = recommends.size();
        if (total == 0){
            for (RecommendScore recommendScore : recommendScores){
                Recommend recommend = new Recommend();
                recommend.setComic(recommendScore.getComic());
                recommend.setUser(user);
                recommend.setCreateTime(new Date());
                recommend.setUpdateTime(new Date());
                recommendJpaRepository.save(recommend);
                log.debug("save recommend "+recommend.toString());
            }
        }else {
            for (int i = 0; i < total; i++){
                Recommend recommend = new Recommend();
                recommend.setId(recommends.get(i).getId());
                recommend.setComic(recommendScores.get(i).getComic());
                recommend.setUser(user);
                recommend.setCreateTime(recommends.get(i).getCreateTime());
                recommend.setUpdateTime(new Date());
                recommendJpaRepository.save(recommend);
                log.debug("save recommend "+recommend.toString());
            }
        }

        return userId;

    }

    @Override
    public Integer saveRecommendAllUser() {
       List<User> users = userJpaRepository.findAll();
       for (User user : users){
           List<Recommend> recommends = recommendJpaRepository.findAllByUser(user);
           List<RecommendScore> recommendScores = this.getAllComicScore(user.getUserId()).subList(0,10);
           int size =  recommends.size();
           if (size == 0 ){
               for (RecommendScore recommendScore : recommendScores){
                   Recommend recommend = new Recommend();
                   recommend.setComic(recommendScore.getComic());
                   recommend.setUser(user);
                   recommend.setCreateTime(new Date());
                   recommend.setUpdateTime(new Date());
                   recommendJpaRepository.save(recommend);
                   log.debug("save recommend "+recommend.toString());
               }
           }else {
               for (int i = 0 ; i < size ; i++){
                   Recommend recommend = new Recommend();
                   recommend.setComic(recommendScores.get(i).getComic());
                   recommend.setUser(user);
                   recommend.setId(recommends.get(i).getId());
                   recommend.setCreateTime(recommends.get(i).getCreateTime());
                   recommend.setUpdateTime(new Date());
                   recommendJpaRepository.save(recommend);
                   log.debug("save recommend "+recommend.toString());
               }
           }

       }
        return users.size();
    }

    @Override
    public List<Comic> getRecommendByUser(Integer userId) {
        List<Recommend> recommends = new ArrayList<>();
        User user = new User();
        user.setUserId(userId);
        recommends = recommendJpaRepository.findAllByUser(user);
        List<Comic> comics = recommends.stream().map(e-> e.getComic()).collect(Collectors.toList());
        return comics;
    }


    @Override
    public List<Comic> getRandComic(Integer limit) {
        List<Comic> comics = comicJpaRepository.findAll();
        List<Comic> randComics = new ArrayList<>();
        Random random = new Random();
        Integer size = comics.size();
        for (Integer i = 0 ; i < limit ; i++){
            Integer index = random.nextInt(size);
            randComics.add(comics.get(index));
        }
        return randComics;
    }

    public Double getSim(String sourceTags, String comicTags){
        VectorMap vectorMap = new VectorMap(sourceTags,comicTags);
        Double sim = vectorMap.sim();
        return sim;
    }

    @Override
    public String getCollectTags(Integer userId) {
        List<Comic> collectComics = comicJpaRepository.findAllByUserIdAtCollect(userId);
        String collectTags = "";
        for (Comic comic : collectComics){
            collectTags += this.getTags(comic);
        }
        return collectTags;
    }

    @Override
    public String getReadTags(Integer userId) {
        List<Comic> readComics = comicJpaRepository.findAllByUserIdAtRead(userId);
        String readTags = "";
        for (Comic comic : readComics){
            readTags += this.getTags(comic);
        }
        return readTags;
    }

    @Override
    public String getUserTags(Integer userId) {
        String userTags = "";
        userTags = this.getCollectTags(userId) + this.getReadTags(userId);
        return userTags;
    }

    @Override
    public LinkedList<RecommendScore> getAllComicScore(Integer userId) {
        String tags = this.getUserTags(userId);
        LinkedList<RecommendScore> comicScore = new LinkedList<>();
        List<Comic> comics =  comicJpaRepository.findAllNotInCollect(userId);
        int total = comics.size();
        Random random = new Random();
        for (Comic comic : comics){
            RecommendScore recommendScore = new RecommendScore();
            recommendScore.setTags(this.getTags(comic));
            Double sim = this.getSim(tags,this.getTags(comic));
            recommendScore.setScore(sim);
            recommendScore.setComic(comic);
            if (sim == 0){
                Boolean isExisted = true;
                Comic randComic = recommendScore.getComic();
                while (isExisted){
                    randComic = comics.get(random.nextInt(total));
                    isExisted = this.isExistedInScore(comicScore,randComic);
                }
                recommendScore.setComic(randComic);
                comicScore.addLast(recommendScore);
            }else {
                int size = comicScore.size();
                if (sim > 0){
                    if (size != 0 ){
                        if (recommendScore.getScore() >= comicScore.getFirst().getScore() ){
                            comicScore.addFirst(recommendScore);
                        }
                        else if (recommendScore.getScore() <= comicScore.getLast().getScore()){
                            comicScore.addLast(recommendScore);
                        }
                        else {
                            for (int i = 0; i < size - 1 ; i++){
                                if (comicScore.get(i).getScore() > recommendScore.getScore()
                                        && comicScore.get(i+1).getScore() <= recommendScore.getScore()){
                                    comicScore.add(i+1,recommendScore);
                                    break;
                                }
                            }
                        }
                    }else{
                        comicScore.add(recommendScore);
                    }
                }
            }



        }
        return comicScore;
    }

    @Override
    public String getComicTags(String comicId) {
        return this.getTags(comicJpaRepository.getOne(comicId));
    }
}
