package com.lcc.security.service;

import com.lcc.security.dto.UserDto;
import com.lcc.security.utils.ResponseResult;

public interface LoginService {

    ResponseResult login(UserDto userDto);
}
