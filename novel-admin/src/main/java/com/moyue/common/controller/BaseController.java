package com.moyue.common.controller;

import com.moyue.system.domain.UserToken;
import org.springframework.stereotype.Controller;
import com.moyue.common.utils.ShiroUtils;
import com.moyue.system.domain.UserDO;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}