package cn.zucc.edu.mansousou.entity.Dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 点赞数量 DTO。用于存储从 Redis 取出来的被点赞数量
 */
@Data
public class LikedCountDTO implements Serializable {

    private static final long serialVersionUID = -2856160546081194945L;

    private Integer id;

    private Integer count;

    public LikedCountDTO() {
    }

    public LikedCountDTO(Integer id, Integer count) {
        this.id = id;
        this.count = count;
    }
}
