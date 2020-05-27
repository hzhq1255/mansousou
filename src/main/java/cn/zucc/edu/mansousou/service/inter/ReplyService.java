package cn.zucc.edu.mansousou.service.inter;

import cn.zucc.edu.mansousou.entity.jpa.Reply;
import cn.zucc.edu.mansousou.util.Result;
import org.springframework.data.domain.Page;

public interface ReplyService {


    Page<Reply>  getAllByCommentId(Integer commentId,Integer currentPage,Integer pageSize);

    Result addReply(Reply reply);

    Result deleteByReplyId(Integer replyId);

    Result updateReply(Reply reply);

    Result updateLikeNum(Integer replyId, Integer likenum);
}
