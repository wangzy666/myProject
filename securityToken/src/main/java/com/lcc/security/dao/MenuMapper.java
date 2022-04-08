package com.lcc.security.dao;

import com.lcc.security.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/1 9:25
 */
public interface MenuMapper{

    List<String> selectMenuList(@Param("userId") Long userId);
}
