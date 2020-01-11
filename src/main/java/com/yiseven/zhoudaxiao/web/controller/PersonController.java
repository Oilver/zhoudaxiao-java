package com.yiseven.zhoudaxiao.web.controller;

import com.yiseven.zhoudaxiao.common.Const.Const;
import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.common.response.ResponseCode;
import com.yiseven.zhoudaxiao.entity.PersonEntity;
import com.yiseven.zhoudaxiao.service.PersonService;
import com.yiseven.zhoudaxiao.web.request.PersonRequest;
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
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("add")
    public Response add(@Valid @RequestBody PersonRequest personRequest) {
        return personService.addPerson(personRequest);
    }

    @PostMapping("update")
    public Response updatePerson(@RequestBody PersonEntity personEntity) {
        return personService.updatePerson(personEntity);
    }

    @PostMapping("delete")
    public Response delete(@RequestBody int id) {
        return personService.delete(id);
    }

    @PostMapping("queryPersonList")
    public Response queryPersonList() {
        return personService.queryPersonList();
    }

    @PostMapping("queryCurrentPerson")
    public Response queryCurrentPerson(HttpServletRequest request) {
        PersonEntity personEntity = personService.queryCurrentPerson(request.getHeader(Const.VALID_HEARER));
        if (personEntity == null) {
            return Response.createByErrorCode(ResponseCode.NEED_LOGIN);
        }
        return Response.createBySuccess(personEntity);
    }
}
