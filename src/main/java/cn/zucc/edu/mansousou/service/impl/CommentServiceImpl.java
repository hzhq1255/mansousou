package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.jpa.Comment;
import cn.zucc.edu.mansousou.repository.jpa.CommentJpaRepository;
import cn.zucc.edu.mansousou.service.inter.CommentService;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    CommentJpaRepository commentJpaRepository;

    @Autowired
    public void setCommentJpaRepository(CommentJpaRepository commentJpaRepository) {
        this.commentJpaRepository = commentJpaRepository;
    }

    @Override
    public Page<Comment> getAllByComicId(String comicId, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return commentJpaRepository.selectAllByComicId(comicId,pageable);
    }

    @Override
    public Page<Comment> getAllByUserId(Integer userId, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return commentJpaRepository.selectAllByUserId(userId,pageable);
    }

    @Override
    public Page<Comment> getAll(Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return commentJpaRepository.selectAll(pageable);
    }


    @Override
    public Result getCommentByUserIdAndComicId(Integer userId, String comicId) {
        Comment Comment = commentJpaRepository.getCommentByUserIdAndComicId(userId, comicId);
        return Result.success(Comment);
    }

    @Override
    public Result addComment(Comment comment) {
            commentJpaRepository.save(comment);
            return Result.success(comment.getCommentId());


    }

    @Override
    public Result updateComment(Comment comment) {
        commentJpaRepository.updateComment(comment.getCommentId(),comment.getDesc(), comment.getUpdateTime());
        return Result.success(comment.getCommentId());
    }

    @Override
    public  Result updateLikeNum(Integer commentId,Integer likenum){
        commentJpaRepository.updateLikeNum(commentId,likenum);
        return  Result.success(commentId);
    }

    @Override
    public Result deleteByCommentId(Integer commentId) {
        commentJpaRepository.deleteByCommentId(commentId);
        return Result.success(commentId);
    }

    @Override
    public Result deleteAllCommentByUserId(Integer userId) {
        List<Integer> commentIds = commentJpaRepository.deleteAllByUserId(userId).stream().map(e -> e.getCommentId()).collect(Collectors.toList());
        return Result.success(commentIds);
    }
}
