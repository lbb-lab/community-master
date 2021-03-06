package life.majiang.community.controller;

import com.alibaba.fastjson.JSON;
import life.majiang.community.constant.RedisConstant;
import life.majiang.community.dto.CommentCreateDTO;
import life.majiang.community.dto.CommentDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.model.Comment;
import life.majiang.community.model.User;
import life.majiang.community.model.UserInfo;
import life.majiang.community.redis.RedisRepository;
import life.majiang.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by codedrinker on 2019/5/30.
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisRepository redisRepository;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public ResultDTO post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {
//        UserInfo user = (UserInfo) request.getSession().getAttribute("user"); //从session里面获取
//        User user = (User) request.getSession().getAttribute("user");  //第三方登陆user信息

        Cookie[] cookies = request.getCookies();
        String token="";
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("token")){
                token = cookie.getValue();
                break;
            }
        }
        Map<String,String> userMap = redisRepository.getJedis().hgetAll(RedisConstant.UserInfo.REDIS_SESSION_KEY+":"+ token);
        UserInfo user = JSON.parseObject(JSON.toJSONString(userMap),UserInfo.class);
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(Long.valueOf(String.valueOf(user.getId())));
        comment.setLikeCount(0L);
        commentService.insert(comment, user);
        return ResultDTO.okOf();
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
