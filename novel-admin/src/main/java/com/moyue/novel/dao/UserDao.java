package com.moyue.novel.dao;

import com.moyue.common.annotation.SanitizeMap;

import com.moyue.novel.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 王聪
 * @email 1179705413@qq.com
 * @date 2020-12-01 03:49:08
 */
@Mapper
public interface UserDao {

    UserDO get(Long id);

    List<UserDO> list(@SanitizeMap Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserDO user);

    int update(UserDO user);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<Map<Object, Object>> tableSta(Date minDate);

    List<UserDO> batchGet(List<Long> userIds);
}
