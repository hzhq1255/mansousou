package cn.zucc.edu.mansousou.controller;


import cn.zucc.edu.mansousou.entity.jpa.Reply;
import cn.zucc.edu.mansousou.service.inter.RedisService;
import cn.zucc.edu.mansousou.service.inter.ReplyService;
import cn.zucc.edu.mansousou.util.PageUtil;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @Autowired
    RedisService redisService;

    @RequestMapping(value = "/getAllReplyByCommentId",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getAllReplyByCommentId(@RequestParam("commentId") @NotNull Integer commentId,
                                        @RequestParam("currentPage") @NotNull Integer currentPage,
                                        @RequestParam("pageSize") @NotNull Integer pageSize){
        if (commentId == 0 ){
            return Result.build(400,"评论id不能为空");
        }else if (currentPage <= 0) {
            return Result.build(400, "当前页面参数不能为空");
        }else if ( pageSize <= 0 ){
            return Result.build(400,"页面尺寸不能为空");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE ){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        Object data = PageUtil.getPageData(replyService.getAllByCommentId(commentId, currentPage-1, pageSize));
        return Result.success(data);
    }

    @RequestMapping(value = "/addReply",method = {RequestMethod.POST})
    public Result addReply(@RequestParam("commentId") @NotNull Integer commentId,
                             @RequestParam("fromUserId") @NotNull Integer fromUserId ,
                             @RequestParam("toUserId") @NotNull Integer toUserId,
                             @RequestParam("content") @NotNull String content){
        Reply reply =new Reply();
        reply.setCommentId(commentId);
        reply.setFromUserId(fromUserId);
        reply.setToUserId(toUserId);
        reply.setContent(content);
        reply.setLikenum(0);
        return replyService.addReply(reply);
    }

    @RequestMapping(value = "/updateReply",method = {RequestMethod.POST})
    public Result updateReply(@RequestParam("replyId") @NotNull Integer replyId, @RequestParam("content") @NotNull String content){
        Reply reply =new Reply();
        reply.setReplyId(replyId);
        reply.setContent(content);
        return  replyService.updateReply(reply);
    }

    @RequestMapping(value = "/deleteByReplyId",method = {RequestMethod.POST})
    public Result deleteByReplyId(@RequestParam("replyId") @NotNull Integer replyId){
        return replyService.deleteByReplyId(replyId);
    }


    @RequestMapping(value = "/replylike",method = {RequestMethod.POST})
    public Result replylike(@RequestParam("replyId")  Integer replyId,
                            @RequestParam("userId")  Integer userId) {
        //先把数据存到Redis里,再定时存回数据库
        redisService.saveReplyLiked2Redis(replyId,userId);
        redisService.incrementReplyLikedCount(replyId);
        return Result.success(replyId);
    }

    @RequestMapping(value =  "/replyunlike",method = {RequestMethod.POST})
    public Result replyunlike(@RequestParam("replyId") Integer replyId,
                              @RequestParam("userId") Integer userId) {
        //取消点赞,先存到Redis里面，再定时写到数据库里
        redisService.replyunlikeFromRedis(replyId,userId);
        redisService.decrementReplyLikedCount(replyId);
        return Result.success(replyId);
    }

}
