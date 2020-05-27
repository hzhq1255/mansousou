package cn.zucc.edu.mansousou.service.impl;

import cn.zucc.edu.mansousou.entity.Dto.ReplyLike;
import cn.zucc.edu.mansousou.entity.jpa.Comment;
import cn.zucc.edu.mansousou.entity.jpa.Reply;
import cn.zucc.edu.mansousou.repository.jpa.CommentJpaRepository;

import cn.zucc.edu.mansousou.repository.jpa.ComtLikeRepository;
import cn.zucc.edu.mansousou.repository.jpa.ReplyJpaRepository;
import cn.zucc.edu.mansousou.repository.jpa.ReplyLikeRepository;
import cn.zucc.edu.mansousou.service.inter.CommentService;
import cn.zucc.edu.mansousou.service.inter.LikedService;
import cn.zucc.edu.mansousou.entity.Dto.ComtLike;
import cn.zucc.edu.mansousou.entity.Dto.LikedCountDTO;
import cn.zucc.edu.mansousou.entity.Dto.LikedStatusEnum;
import cn.zucc.edu.mansousou.service.inter.RedisService;


import cn.zucc.edu.mansousou.service.inter.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class LikedServiceImpl implements LikedService {

    @Autowired
    ComtLikeRepository comtLikeRepository;

    @Autowired
    CommentJpaRepository commentJpaRepository;

    @Autowired
    ReplyJpaRepository replyJpaRepository;

    @Autowired
    ReplyLikeRepository replyLikeRepository;


    @Autowired
    RedisService redisService;

    @Autowired
    CommentService commentService;

    @Autowired
    ReplyService replyService;

    @Override
    @Transactional
    public ComtLike save(ComtLike comtLike) {
        return  comtLikeRepository.save(comtLike);
    }

    @Override
    @Transactional
    public ReplyLike saveReply(ReplyLike replyLike){return  replyLikeRepository.save(replyLike);}

    @Override
    @Transactional
    public List<ComtLike> saveAll(List<ComtLike> list) {
        return comtLikeRepository.saveAll(list);
    }

    @Override
    @Transactional
    public  List<ReplyLike> saveReplyAll(List<ReplyLike> list){return  replyLikeRepository.saveAll(list);}

    @Override
    public Page<ComtLike> getLikedListByCommentId(Integer commentId, Pageable pageable) {
        return comtLikeRepository.findByCommentIdAndStatus(commentId, LikedStatusEnum.LIKE.getCode(), pageable);
    }

    @Override
    public Page<ReplyLike> getLikedListByReplyId(Integer replyId, Pageable pageable) {
        return replyLikeRepository.findByReplyIdAndStatus(replyId, LikedStatusEnum.LIKE.getCode(), pageable);
    }

    @Override
    public Page<ReplyLike> getReplyLikedListByUserId(Integer userId, Pageable pageable) {
        return replyLikeRepository.findByUserIdAndStatus(userId, LikedStatusEnum.LIKE.getCode(), pageable);
    }


    @Override
    public Page<ComtLike> getLikedListByUserId(Integer userId, Pageable pageable) {
        return comtLikeRepository.findByUserIdAndStatus(userId, LikedStatusEnum.LIKE.getCode(), pageable);
    }

    @Override
    public ReplyLike getByReplyIdAndUserId(Integer replyId,Integer userId) {
        return replyLikeRepository.findByReplyIdAndUserId(replyId,userId);
    }

    @Override
    public ComtLike getByCommentIdAndUserId(Integer commentId,Integer userId) {
        return comtLikeRepository.findByCommentIdAndUserId(commentId,userId);
    }

    @Override
    @Transactional
    public void transLikedFromRedis2DB() {
        List<ComtLike> list = redisService.getComtLikedDataFromRedis();
        for (ComtLike like : list) {
            ComtLike ul = getByCommentIdAndUserId(like.getCommentId(),like.getUserId());
            if (ul == null){
                //没有记录，直接存入
                save(like);
            }else{
                //有记录，需要更新
                ul.setStatus(like.getStatus());
                save(ul);
            }
        }
    }

    @Override
    @Transactional
    public void transReplyLikedFromRedis2DB() {
        List<ReplyLike> list = redisService.getReplyLikedDataFromRedis();
        for (ReplyLike like : list) {
            ReplyLike ul = getByReplyIdAndUserId(like.getReplyId(),like.getUserId());
            if (ul == null){
                //没有记录，直接存入
                saveReply(like);
            }else{
                //有记录，需要更新
                ul.setStatus(like.getStatus());
                saveReply(ul);
            }
        }
    }


    @Override
    @Transactional
    public void transLikedCountFromRedis2DB() {
        List<LikedCountDTO> list = redisService.getLikedCountFromRedis();
        for (LikedCountDTO dto : list) {

            Comment comment =commentJpaRepository.findByCommentId(dto.getId());
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (comment != null){

//                System.out.println(comment);

                Integer likenum = comment.getLikenum() + dto.getCount();
                //更新评论点赞数量
               commentService.updateLikeNum(comment.getCommentId(),likenum);
            }
        }
    }

    @Override
    @Transactional
    public void transReplyLikedCountFromRedis2DB() {
        List<LikedCountDTO> list = redisService.getReplyLikedCountFromRedis();
        for (LikedCountDTO dto : list) {

            Reply reply  = replyJpaRepository.findByReplyId(dto.getId());
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (reply != null){

                Integer likenum = reply.getLikenum() + dto.getCount();
                //更新回复点赞数量
                replyService.updateLikeNum(reply.getReplyId(),likenum);
            }
        }
    }


}
