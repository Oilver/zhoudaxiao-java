package com.yiseven.zhoudaxiao.service;


import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.entity.UserEntity;
import com.yiseven.zhoudaxiao.web.request.UserRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hdeng
 */
public interface UserService {

    Response addUser(UserRequest userRequest);

    Response updateUser(UserEntity userEntity);

    Response queryUser(String phone);

    UserEntity queryCurrentUser(String token);

    Response queryUserList();

    Response delete(int id);

}
