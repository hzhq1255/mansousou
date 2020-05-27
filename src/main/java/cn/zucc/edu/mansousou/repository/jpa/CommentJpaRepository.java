package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.jpa.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Repository
public interface CommentJpaRepository extends JpaRepository<Comment,Integer> {

    /**
     * 通过用户 id 得到评论记录
     * @param userId
     * @param pageable
     * @return
     */
    @Query("select comment from Comment comment where comment.userId =:userId order by comment.updateTime desc ")
    Page<Comment> selectAllByUserId(@Param("userId") Integer userId, Pageable pageable);

    /**
     * 通过漫画 id 得到评论记录
     * @param comicId
     * @param pageable
     * @return
     */
    @Query("select comment from Comment comment where comment.comicId =:comicId order by comment.updateTime desc ")
    Page<Comment> selectAllByComicId(@Param("comicId") String comicId, Pageable pageable);

    /**
     * 通过 userId 和 comicId 获取 Comment
     * @param userId
     * @param comicId
     * @return
     */
    Comment getCommentByUserIdAndComicId(Integer userId,String comicId);

    /**
     * 获取所有评论
     * @param pageable
     * @return
     */
    @Query("select comment from Comment comment order by comment.updateTime desc ")
    Page<Comment> selectAll(Pageable pageable);

    /**
     * 评论修改
     * @param commentId
     * @param desc
     * @param updateTime
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    @Query(value = "update Comment comment set comment.desc =:desc, comment.updateTime =:updateTime where comment.commentId =:commentId")
    Integer updateComment(@Param("commentId") Integer commentId, @Param("desc") String desc, @Param("updateTime")Date updateTime);


    @Modifying
    @Transactional(rollbackFor=Exception.class)
    @Query(value = "update Comment comment set comment.likenum =:likenum where comment.commentId =:commentId")
    Integer updateLikeNum(@Param("commentId") Integer commentId, @Param("likenum") Integer likenum);


    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    Integer deleteByCommentId(Integer  commentId);

    /**
     * 删除用户评论
     * @param userId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    List<Comment> deleteAllByUserId(Integer userId);

    Comment findByCommentId(Integer commentId);

}
