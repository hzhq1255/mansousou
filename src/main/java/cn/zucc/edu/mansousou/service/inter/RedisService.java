package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.Dto.LikedCountDTO;
import cn.zucc.edu.mansousou.entity.Dto.ComtLike;
import cn.zucc.edu.mansousou.entity.Dto.ReplyLike;
import java.util.List;
import java.util.Set;

public interface RedisService {


    void saveSearch2Redis(String keyword);

    Set<Object> getHotSearchTop10();

    /**
     * 评论点赞。状态为1
     */
    void saveLiked2Redis(Integer commentId, Integer userId);

    void saveReplyLiked2Redis(Integer replyId,Integer userId);


    /**
     * 取消评论点赞。将状态改变为0
     */
    void unlikeFromRedis(Integer commentId, Integer userId);

    void  replyunlikeFromRedis(Integer replyId,Integer userId);

    /**
     * 从Redis中删除一条点赞数据
     */
    void deleteLikedFromRedis(Integer commentId, Integer userId);

    void deleteReplyLikedFromRedis(Integer replyId,Integer userId);

    /**
     * 该评论的点赞数加1
     */
    void incrementLikedCount(Integer commentId);

    void incrementReplyLikedCount(Integer replyId);
    /**
     * 该评论的点赞数减1
     */
    void decrementLikedCount(Integer commentId);

    void decrementReplyLikedCount(Integer replyId);

    /**
     * 获取Redis中存储的所有点赞数据
     */
    List<ComtLike> getComtLikedDataFromRedis();

    List<ReplyLike> getReplyLikedDataFromRedis();

    /**
     * 获取Redis中存储的所有评论点赞数量
     */
    List<LikedCountDTO> getLikedCountFromRedis();

    List<LikedCountDTO> getReplyLikedCountFromRedis();

}
