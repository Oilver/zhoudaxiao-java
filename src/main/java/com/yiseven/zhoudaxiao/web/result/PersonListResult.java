package com.yiseven.zhoudaxiao.web.result;

import com.yiseven.zhoudaxiao.entity.PersonEntity;
import lombok.Data;

import java.util.List;

/**
 * @author hdeng
 */
@Data
public class PersonListResult {

    List<PersonEntity> personList;

    List<PersonEntity> unPassList;

}
