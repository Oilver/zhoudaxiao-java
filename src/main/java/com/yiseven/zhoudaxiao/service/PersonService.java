package com.yiseven.zhoudaxiao.service;


import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.entity.PersonEntity;
import com.yiseven.zhoudaxiao.web.request.PersonRequest;

/**
 * @author hdeng
 */
public interface PersonService {

    Response addPerson(PersonRequest personRequest);

    Response updatePerson(PersonEntity personEntity);

    Response queryPerson(String phone);

    PersonEntity queryCurrentPerson(String token);

    Response queryPersonList();

    Response delete(int id);

}
