package com.yiseven.zhoudaxiao.web.controller;

import com.yiseven.zhoudaxiao.common.Const.Const;
import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.common.response.ResponseCode;
import com.yiseven.zhoudaxiao.entity.UserEntity;
import com.yiseven.zhoudaxiao.service.UserService;
import com.yiseven.zhoudaxiao.web.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author hdeng
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public Response addUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    @PostMapping("update")
    public Response updateUser(@RequestBody UserEntity userEntity) {
        return userService.updateUser(userEntity);
    }

    @PostMapping("delete")
    public Response delete(@RequestBody int id) {
        return userService.delete(id);
    }

    @PostMapping("queryUserList")
    public Response queryUserList() {
        return userService.queryUserList();
    }

    @PostMapping("queryCurrentUser")
    public Response queryCurrentUser(HttpServletRequest request) {
        UserEntity userEntity = userService.queryCurrentUser(request.getHeader(Const.VALID_HEARER));
        if (userEntity == null) {
            return Response.createByErrorCode(ResponseCode.NEED_LOGIN);
        }
        return Response.createBySuccess(userEntity);
    }
}
