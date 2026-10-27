package com.moyue.novel.service.impl;

import com.moyue.novel.dao.PayDao;
import com.moyue.novel.dao.UserDao;
import com.moyue.novel.domain.PayDO;
import com.moyue.novel.domain.UserDO;
import com.moyue.novel.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayDao payDao;
    @Autowired
    private UserDao userDao;

    @Override
    public PayDO get(Long id) {
        return payDao.get(id);
    }

    @Override
    public List<PayDO> list(Map<String, Object> map) {
        List<PayDO> list = payDao.list(map);
        if (!CollectionUtils.isEmpty(list)) {
            List<Long> userIds = list.stream().map(PayDO::getUserId).collect(Collectors.toList());
            Map<Long, String> userNameMap = userDao.batchGet(userIds).stream()
                .collect(Collectors.toMap(UserDO::getId, UserDO::getUsername));
            list.forEach(v -> v.setUserName(userNameMap.get(v.getUserId())));
        }
        return list;
    }

    @Override
    public int count(Map<String, Object> map) {
        return payDao.count(map);
    }

    @Override
    public int save(PayDO pay) {
        return payDao.save(pay);
    }

    @Override
    public int update(PayDO pay) {
        return payDao.update(pay);
    }

    @Override
    public int remove(Long id) {
        return payDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return payDao.batchRemove(ids);
    }

    @Override
    public Map<Object, Object> tableSta(Date minDate) {
        List<Map<Object, Object>> maps = payDao.tableSta(minDate);

        return maps.stream().collect(Collectors.toMap(x -> x.get("staDate"), x -> x.get("orderCount")));


    }

}
