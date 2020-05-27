package cn.zucc.edu.mansousou.entity.Dto;

import lombok.Data;

import javax.persistence.*;


/**
 * 用户点赞表
 */
@Data
@Entity
@Table(name = "reply_like")
public class ReplyLike {


    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    //被点赞的用户的id
    private Integer userId;


    //点赞的用户的id
    private Integer replyId;


    //点赞的状态.默认未点赞
    private Integer status = LikedStatusEnum.UNLIKE.getCode();


    public ReplyLike() {
    }


    public  ReplyLike( Integer replyId, Integer userId,Integer status) {
        this.replyId = replyId;
        this.userId = userId;
        this.status = status;
    }
}
