package cn.zucc.edu.mansousou.controller;

import cn.zucc.edu.mansousou.entity.jpa.Comment;
import cn.zucc.edu.mansousou.service.inter.CommentService;
import cn.zucc.edu.mansousou.service.inter.RedisService;
import cn.zucc.edu.mansousou.util.PageUtil;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    RedisService redisService;



    @RequestMapping(value = "/getAllCommentByUserId",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllCommentByUserId(@RequestParam("userId") @NotNull Integer userId,
                                        @RequestParam("currentPage") @NotNull Integer currentPage,
                                        @RequestParam("pageSize") @NotNull Integer pageSize){
        if (userId == 0 ){
            return Result.build(400,"用户不能为空");
        }else if (currentPage <= 0) {
            return Result.build(400, "当前页面参数不能为空");
        }else if ( pageSize <= 0 ){
            return Result.build(400,"页面尺寸不能为空");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE ){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        Object data = PageUtil.getPageData(commentService.getAllByUserId(userId, currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/getAllCommentByComicId",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllCommentByComicId(@RequestParam("comicId") @NotNull String comicId,
                                   @RequestParam("currentPage") @NotNull Integer currentPage,
                                   @RequestParam("pageSize") @NotNull Integer pageSize){
        if (comicId.isEmpty() ){
            return Result.build(400,"漫画id不能为空");
        }else if ( currentPage <= 0) {
            return Result.build(400, "当前页面参数不能为空");
        }else if ( pageSize <= 0 ){
            return Result.build(400,"页面尺寸不能为空");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE ){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        Object data = PageUtil.getPageData(commentService.getAllByComicId(comicId, currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/getAllComment",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllComment(@RequestParam("currentPage") @NotNull Integer currentPage,
                                @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage <= 0 || pageSize <= 0){
            return Result.error("参数错误");
        }
        Object data = PageUtil.getPageData(commentService.getAll(currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/getCommentByUserIdAndComicId",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getCommentByUserIdAndComicId(@RequestParam("userId") @NotNull Integer userId,
                                               @RequestParam("comicId") @NotNull String comicId){
        return commentService.getCommentByUserIdAndComicId(userId, comicId);
    }



    @RequestMapping(value = "/addComment",method = {RequestMethod.POST})
    public Result addComment(@RequestParam("userId") @NotNull Integer userId,
                             @RequestParam("comicId") @NotNull String comicId,
                             @RequestParam("desc") @NotNull String desc){
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setComicId(comicId);
        comment.setDesc(desc);
        comment.setLikenum(0);
        comment.setUpdateTime(new Date());
        return commentService.addComment(comment);
    }

    @RequestMapping(value = "/updateComment",method = {RequestMethod.POST})
    public Result updateComment(@RequestParam("commentId") @NotNull Integer commentId, @RequestParam("desc") @NotNull String desc){
        Comment comment = new Comment();
        comment.setCommentId(commentId);
        comment.setDesc(desc);
        comment.setUpdateTime(new Date());
        return commentService.updateComment(comment);
    }

    @RequestMapping(value = "/deleteByCommentId",method = {RequestMethod.POST})
    public Result deleteByCommentId(@RequestParam("commentId") @NotNull Integer commentId){
        return commentService.deleteByCommentId(commentId);
    }

    @RequestMapping(value = "/deleteAllCommentByUserId",method = {RequestMethod.POST})
    public Result deleteAllCollectByUserId(@RequestParam("userId") @NotNull Integer userId){
        return commentService.deleteAllCommentByUserId(userId);
    }

    @RequestMapping(value = "/like",method = {RequestMethod.POST})
    public Result like(@RequestParam("commentId")  Integer commentId,
                         @RequestParam("userId")  Integer userId) {
        //先把数据存到Redis里,再定时存回数据库
        redisService.saveLiked2Redis(commentId,userId);
        redisService.incrementLikedCount(commentId);
        return Result.success(commentId);
    }

    @RequestMapping(value =  "/unlike",method = {RequestMethod.POST})
    public Result unlike(@RequestParam("commentId") Integer commentId,
                           @RequestParam("userId") Integer userId) {
        //取消点赞,先存到Redis里面，再定时写到数据库里
        redisService.unlikeFromRedis(commentId,userId);
        redisService.decrementLikedCount(commentId);
        return Result.success(commentId);
    }

}
