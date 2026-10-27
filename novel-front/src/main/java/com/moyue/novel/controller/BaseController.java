package com.moyue.novel.controller;

import com.moyue.novel.core.bean.UserDetails;
import com.moyue.novel.core.utils.CookieUtil;
import com.moyue.novel.core.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 */
public class BaseController {

    protected JwtTokenUtil jwtTokenUtil;


    protected String getToken(HttpServletRequest request){
        String token = CookieUtil.getCookie(request,"Authorization");
        if(token != null){
            return token;
        }
        return request.getHeader("Authorization");
    }

    protected UserDetails getUserDetails(HttpServletRequest request) {
        String token = getToken(request);
        if(StringUtils.isBlank(token)){
            return null;
        }else{
            return jwtTokenUtil.getUserDetailsFromToken(token);
        }
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }
}
