package com.otc.landmark.web.service.impl;

import com.otc.landmark.web.Utils.DTOConvert;
import com.otc.landmark.web.Utils.DateUtil;
import com.otc.landmark.web.domain.Comment;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.domain.User;
import com.otc.landmark.web.dto.CommentDto;
import com.otc.landmark.web.repository.EntryDao;
import com.otc.landmark.web.repository.UserDao;
import com.otc.landmark.web.repository.impl.CommentDaoImpl;
import com.otc.landmark.web.repository.impl.EntryDaoImpl;
import com.otc.landmark.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class CommentServiceImpl {

    public void addComment(CommentDto commentDto) {
        Entry entry = entryDao.findById(commentDto.getEntryId());
        User user = userDao.findById(commentDto.getUserId());
        if(entry == null || user == null) {
            commentDto.setResult("false");
        } else {
            Comment comment = new Comment();
            comment.setEntryId(entry.getId());
            comment.setUser(user);
            comment.setBody(commentDto.getComment());
            comment.setCreatedDate(DateUtil.getSystemDateTime());
            comment.setCreatedBy(1L);
            commentDao.save(comment);
            Integer countComment = commentDao.countComment(entry.getId());
            commentDto.setCountComment(countComment);
            commentDto.setResult("true");
            Integer limit = 3;
            List<Comment> comments = commentDao.findAllWithMaxResults(entry.getId(), 0,limit);
            List<CommentDto> commentDtos = new ArrayList<>();
            DTOConvert.convertListComment2DTO(comments, commentDtos);
            commentDto.setThreeCommentNewest(commentDtos);
        }
    }

    public List<CommentDto> comments (Long entryId, Integer limit) {
        List<Comment> comments = commentDao.findAllWithMaxResults(entryId, 0,limit);
        List<CommentDto> commentDtos = new ArrayList<>();
        DTOConvert.convertListComment2DTO(comments, commentDtos);
        return commentDtos;
    }

    public Integer countComment (Long entryId) {
        Integer countComment = commentDao.countComment(entryId);
        return countComment;
    }

    public List<CommentDto> getCommentLimit(Long entryId, Integer first, Integer max) {
        List<Comment> comments = commentDao.findAllWithMaxResults(entryId, first, max);
        Integer countComment = commentDao.countComment(entryId);
        List<CommentDto> commentDtos = new ArrayList<>();
        DTOConvert.convertListComment2DTO(comments, commentDtos);
        for (CommentDto commentDto : commentDtos) {
            commentDto.setCountComment(countComment);
        }
        return commentDtos;
    }

    @Autowired
    private EntryDao entryDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommentDaoImpl commentDao;
}
