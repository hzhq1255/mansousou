package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.jpa.Comment;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.data.domain.Page;


public interface CommentService {

    /**
     * 分页通过漫画 名字 获取收藏并分页
     * @param comicId
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Comment> getAllByComicId(String comicId,Integer currentPage,Integer pageSize);

    /**
     * 分页通过用户 id 获取收藏并分页
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Comment> getAllByUserId(Integer userId, Integer currentPage, Integer pageSize);

    /**
     * 获取所有的收藏
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Comment> getAll(Integer currentPage,Integer pageSize);

    /**
     * 通过 userId 和 comicId 获取 Comment
     * @param userId
     * @param comicId
     * @return
     */
    Result getCommentByUserIdAndComicId(Integer userId,String comicId);

    /**
     * 添加收藏
     * @param comment
     * @return
     */
    Result addComment(Comment comment);

    /**
     * 修改收藏
     * @param comment
     * @return
     */
    Result updateComment(Comment comment);

    /**
     * 删除收藏
     * @param commentId
     * @return
     */
    Result deleteByCommentId(Integer commentId);

    /**
     * 删除用户所有收藏
     * @param userId
     * @return
     */
    Result deleteAllCommentByUserId(Integer userId);

    Result updateLikeNum(Integer commentId,Integer likenum);
}
