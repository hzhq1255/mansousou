package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.Dto.ComtLike;
import cn.zucc.edu.mansousou.entity.Dto.ReplyLike;
import  cn.zucc.edu.mansousou.entity.Dto.LikedCountDTO;
import cn.zucc.edu.mansousou.entity.Dto.LikedStatusEnum;
import cn.zucc.edu.mansousou.service.inter.LikedService;
import cn.zucc.edu.mansousou.service.inter.RedisService;
import  cn.zucc.edu.mansousou.util.RedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    LikedService likedService;


    @Override
    public  void saveSearch2Redis(String keyword){
        String member= RedisKeyUtils.getSearchKey(keyword);
        redisTemplate.opsForZSet().incrementScore(RedisKeyUtils.MAP_KEY_HOT_SEARCH,member,1);

    }

    @Override
    public Set<Object> getHotSearchTop10(){
        return  redisTemplate.opsForZSet().reverseRangeWithScores(RedisKeyUtils.MAP_KEY_HOT_SEARCH,0,9);
    }


    @Override
    public void saveLiked2Redis(Integer commentId, Integer userId) {
        String key = RedisKeyUtils.getComtLikedKey(commentId,userId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_COMT_LIKED, key, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public void saveReplyLiked2Redis(Integer replyId, Integer userId) {
        String key = RedisKeyUtils.getReplyLikedKey(replyId,userId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_REPLY_LIKED, key, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public void unlikeFromRedis(Integer commentId, Integer userId) {
        String key = RedisKeyUtils.getComtLikedKey(commentId,userId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_COMT_LIKED, key, LikedStatusEnum.UNLIKE.getCode());
    }

    @Override
    public void replyunlikeFromRedis(Integer replyId, Integer userId) {
        String key = RedisKeyUtils.getReplyLikedKey(replyId,userId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_REPLY_LIKED, key, LikedStatusEnum.UNLIKE.getCode());
    }

    @Override
    public void deleteLikedFromRedis(Integer commentId, Integer userId) {
        String key = RedisKeyUtils.getComtLikedKey(commentId,userId);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_COMT_LIKED, key);
    }

    @Override
    public void deleteReplyLikedFromRedis(Integer replyId, Integer userId) {
        String key = RedisKeyUtils.getReplyLikedKey(replyId,userId);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_REPLY_LIKED, key);
    }

    @Override
    public void incrementLikedCount(Integer commentId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_COMT_LIKED_COUNT, commentId, 1);
    }

    @Override
    public void incrementReplyLikedCount(Integer replyId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_REPLY_LIKED_COUNT, replyId, 1);
    }

    @Override
    public void decrementLikedCount(Integer commentId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_COMT_LIKED_COUNT, commentId, -1);
    }

    @Override
    public void decrementReplyLikedCount(Integer replyId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_REPLY_LIKED_COUNT, replyId, -1);
    }

    @Override
    public List<ComtLike> getComtLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_COMT_LIKED, ScanOptions.NONE);
        List<ComtLike> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 commentId，userId
            String[] split = key.split("::");
            Integer commentId = Integer.valueOf(split[0]);
            Integer  userId = Integer.valueOf(split[1]);
            Integer value = (Integer) entry.getValue();

            //组装成 ComtLike 对象
            ComtLike comtLike = new ComtLike(commentId, userId, value);
            list.add(comtLike);

            //存到 list 后从 Redis 中删除
//            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
        }

        return list;
    }

    @Override
    public List<ReplyLike> getReplyLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_REPLY_LIKED, ScanOptions.NONE);
        List<ReplyLike> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 replyId，userId
            String[] split = key.split("::");
            Integer replyId = Integer.valueOf(split[0]);
            Integer  userId = Integer.valueOf(split[1]);
            Integer value = (Integer) entry.getValue();

            //组装成 ComtLike 对象
            ReplyLike replyLike = new ReplyLike(replyId, userId, value);
            list.add(replyLike);

            //存到 list 后从 Redis 中删除
//            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
        }

        return list;
    }

    @Override
    public List<LikedCountDTO> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_COMT_LIKED_COUNT, ScanOptions.NONE);
        List<LikedCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //将点赞数量存储在 LikedCountDTO
            Integer key = (Integer) map.getKey();
            LikedCountDTO dto = new LikedCountDTO(key, (Integer) map.getValue());
            list.add(dto);
            //从Redis中删除这条记录
//            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, key);
        }
        return list;
    }

    @Override
    public List<LikedCountDTO> getReplyLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_REPLY_LIKED_COUNT, ScanOptions.NONE);
        List<LikedCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //将点赞数量存储在 LikedCountDTO
            Integer key = (Integer) map.getKey();
            LikedCountDTO dto = new LikedCountDTO(key, (Integer) map.getValue());
            list.add(dto);
            //从Redis中删除这条记录
//            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, key);
        }
        return list;
    }

}
