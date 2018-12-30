package org.sopt.artoo.service;

import lombok.extern.slf4j.Slf4j;
import org.sopt.artoo.dto.Comment;
import org.sopt.artoo.mapper.CommentMapper;
import org.sopt.artoo.model.CommentReq;
import org.sopt.artoo.model.DefaultRes;
import org.sopt.artoo.utils.ResponseMessage;
import org.sopt.artoo.utils.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CommentService {

    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public DefaultRes<List<Comment>> findAllCommentByArtIdx(final int a_idx) {
        List<Comment> comments = commentMapper.findAllCommentByArtIdx(a_idx);
        try {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_ALL_COMMENTS, comments);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    public DefaultRes<Comment> findCommentByCommentIdx(final int c_idx) {
        Comment comment = commentMapper.findCommentByCommentIdx(c_idx);
        try {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_COMMENT, comment);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    public DefaultRes<List<Comment>> findAllCommentByUserIdx(final int u_idx) {
        List<Comment> comments = commentMapper.findAllCommentByUserIdx(u_idx);
        try {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_ALL_COMMENTS, comments);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    /**
     * 댓글 작성
     *
     * @param commentReq 댓글 데이터
     * @return DefaulltRes
     */
    @Transactional
    public DefaultRes saveComment(final CommentReq commentReq) {
        if(commentReq.checkProperties()){
            try {
                Date date = new Date();
                commentReq.setC_date(date);
                commentMapper.saveComment(commentReq);
                return DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATE_COMMENT);
            } catch (Exception e) {
                log.info(e.getMessage());
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
            }
        }else {
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.FAIL_CREATE_COMMENT);
        }
    }
}