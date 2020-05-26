package com.movies.rest;

import com.movies.model.UserAction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "UserActionRecord", url = "http://www.mocky.io/v2")
public interface UserActionRecordClient {

    /**
     * The user id here is not forwarded. This is an intentional behaviour.
     */
    @RequestMapping(value = "/5ecc8cbd3200005b002361b9", method = RequestMethod.GET)
    UserAction[] retrieveAllUserActions(final int userId);
}
