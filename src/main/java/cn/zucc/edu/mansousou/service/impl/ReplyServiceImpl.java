package cn.zucc.edu.mansousou.service.impl;


import cn.zucc.edu.mansousou.entity.jpa.Reply;
import cn.zucc.edu.mansousou.repository.jpa.ReplyJpaRepository;
import cn.zucc.edu.mansousou.service.inter.ReplyService;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import  org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class ReplyServiceImpl implements ReplyService {

    ReplyJpaRepository replyJpaRepository;

    @Autowired
    public  void setReplyJpaRepository(ReplyJpaRepository replyJpaRepository){
        this.replyJpaRepository = replyJpaRepository;
    }

    @Override
    public Page<Reply> getAllByCommentId(Integer commentId,Integer currentPage, Integer pageSize){

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return  replyJpaRepository.findAllByCommentId(commentId,pageable);
    }

    @Override
    public  Result addReply(Reply reply){
        replyJpaRepository.save(reply);
        return  Result.success(reply.getReplyId());
    }

    @Override
    public  Result deleteByReplyId(Integer replyId){
        replyJpaRepository.deleteByReplyId(replyId);
        return  Result.success(replyId);
    }

    @Override
    public Result updateReply(Reply reply){
        replyJpaRepository.updateReply(reply.getReplyId(),reply.getContent());
        return  Result.success(reply.getReplyId());
    }

    @Override
    public  Result updateLikeNum(Integer replyId,Integer likenum){
        replyJpaRepository.updateLikeNum(replyId,likenum);
        return  Result.success(replyId);
    }


}
