package cn.zucc.edu.mansousou.repository.jpa;


import cn.zucc.edu.mansousou.entity.jpa.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReplyJpaRepository extends JpaRepository<Reply,Integer> {

    /**
     * 通过评论id得到评论的所有回复
     * @param commentId
     * @param pageable
     * @return
     */

    Page<Reply> findAllByCommentId(@Param("commentId") Integer commentId, Pageable pageable);


    /**
     * 回复修改
     * @param replyId
     * @param content
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    @Query(value = "update Reply reply set reply.content =:content  where reply.replyId =:replyId")
    Integer updateReply(@Param("replyId") Integer replyId, @Param("content") String content);


    @Modifying
    @Transactional(rollbackFor=Exception.class)
    @Query(value = "update Reply reply set reply.likenum =:likenum where reply.replyId =:replyId")
    Integer updateLikeNum(@Param("replyId") Integer replyId, @Param("likenum") Integer likenum);


    /**
     * 删除某条回复
     * @param replyId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    Integer deleteByReplyId(Integer  replyId);


    Reply findByReplyId(Integer replyId);

}
