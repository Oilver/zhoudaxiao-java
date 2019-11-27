package com.yiseven.zhoudaxiao.web.result;

import com.yiseven.zhoudaxiao.entity.UserEntity;
import lombok.Data;

import java.util.List;

/**
 * @author hdeng
 */
@Data
public class UserListResult {

    List<UserEntity> userList;

    List<UserEntity> unPassList;

}
