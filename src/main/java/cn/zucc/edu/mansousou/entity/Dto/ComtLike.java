package cn.zucc.edu.mansousou.entity.Dto;


import lombok.Data;

import javax.persistence.*;


/**
 * 用户点赞表
 */
@Data
@Entity
@Table(name = "comt_like")
public class ComtLike {


    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    //被点赞的用户的name
    private Integer userId;


    //点赞的用户的id
    private Integer commentId;


    //点赞的状态.默认未点赞
    private Integer status = LikedStatusEnum.UNLIKE.getCode();


    public ComtLike() {
    }


    public ComtLike( Integer commentId, Integer userId, Integer status) {
        this.commentId = commentId;
        this.userId = userId;
        this.status = status;
    }
}
