package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.Dto.ReplyLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ReplyLikeRepository extends JpaRepository<ReplyLike, Integer> {

    Page<ReplyLike> findByReplyIdAndStatus(Integer replyId, Integer status, Pageable pageable);

    Page<ReplyLike> findByUserIdAndStatus(Integer userId, Integer status, Pageable pageable);

    ReplyLike findByReplyIdAndUserId(Integer replyId, Integer userId);



}
