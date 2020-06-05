package cn.zucc.edu.mansousou.service.inter;


import cn.zucc.edu.mansousou.entity.Dto.ComtLike;
import cn.zucc.edu.mansousou.entity.Dto.ReplyLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;


public interface LikedService {


//    Boolean IsComtLiked(Integer userid,Integer commentid);


    /**
     * 保存评论点赞记录
     * @param comtLike
     * @return
     */
    ComtLike save(ComtLike comtLike);

    ReplyLike saveReply(ReplyLike replyLike);

    /**
     * 批量保存或修改
     * @param list
     */
    List<ComtLike> saveAll(List<ComtLike> list);

    List<ReplyLike> saveReplyAll(List<ReplyLike> list);


    /**
     * 根据被点赞的评论id查询点赞列表（即查询都谁给这条评论点赞过）
     * @param commentId 被点赞人的评论id
     * @param pageable
     * @return
     */
    Page<ComtLike> getLikedListByCommentId(Integer commentId, Pageable pageable);

    Page<ReplyLike> getLikedListByReplyId(Integer replyId, Pageable pageable);

    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给哪些评论点赞过）
     * @param userId
     * @param pageable
     * @return
     */
    Page<ComtLike> getLikedListByUserId(Integer userId, Pageable pageable);

    Page<ReplyLike> getReplyLikedListByUserId(Integer userId,Pageable pageable);

    /**
     * 通过被点赞评论id和点赞人id查询是否存在点赞记录
     * @param commentId
     * @param userId
     * @return
     */
    ComtLike getByCommentIdAndUserId(Integer commentId,Integer userId);

    ReplyLike getByReplyIdAndUserId(Integer replyId,Integer userId);

    /**
     * 将Redis里的点赞数据存入数据库中
     */
    void transLikedFromRedis2DB();

    void transReplyLikedFromRedis2DB();

    /**
     * 将Redis中的点赞数量数据存入数据库
     */
    void transLikedCountFromRedis2DB();

    void transReplyLikedCountFromRedis2DB();

}
