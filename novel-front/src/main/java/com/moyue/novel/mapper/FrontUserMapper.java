package com.moyue.novel.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 */
public interface FrontUserMapper extends UserMapper {



    void addUserBalance(@Param("userId") Long userId, @Param("amount") Integer amount);


}
