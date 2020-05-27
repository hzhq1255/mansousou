package cn.zucc.edu.mansousou.repository.jpa;

import cn.zucc.edu.mansousou.entity.Dto.ComtLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ComtLikeRepository extends JpaRepository<ComtLike, Integer> {

    Page<ComtLike> findByCommentIdAndStatus(Integer commentId, Integer status, Pageable pageable);

    Page<ComtLike> findByUserIdAndStatus(Integer userId, Integer status, Pageable pageable);

    ComtLike findByCommentIdAndUserId(Integer commentId, Integer userId);



}
