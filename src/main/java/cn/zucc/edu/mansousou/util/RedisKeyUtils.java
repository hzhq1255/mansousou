package cn.zucc.edu.mansousou.util;

public class RedisKeyUtils {


    //保存评论点赞数据的key
    public static final String MAP_KEY_COMT_LIKED = "MAP_COMT_LIKED";
    //保存评论被点赞数量的key
    public static final String MAP_KEY_COMT_LIKED_COUNT = "MAP_COMT_LIKED_COUNT";

    public static  final  String MAP_KEY_REPLY_LIKED = "MAP_REPLY_LIKED";

    public  static  final String MAP_KEY_REPLY_LIKED_COUNT = "MAP_REPLY_LIKED_COUNT";



    /**
     * 拼接被点赞的评论id和点赞的人的id作为key。格式 1::6
     * @param commentId 被点赞的评论id
     * @param userId 点赞的人的id
     * @return
     */
    public static String getComtLikedKey(Integer commentId, Integer userId){
        StringBuilder builder = new StringBuilder();
        builder.append(commentId);
        builder.append("::");
        builder.append(userId);
        return builder.toString();
    }

    /**
     * 拼接被点赞的回复id和点赞的人的id作为key。格式 1::6
     * @param replyId 被点赞的评论id
     * @param userId 点赞的人的id
     * @return
     */
    public static String getReplyLikedKey(Integer replyId, Integer userId){
        StringBuilder builder = new StringBuilder();
        builder.append(replyId);
        builder.append("::");
        builder.append(userId);
        return builder.toString();
    }
}

