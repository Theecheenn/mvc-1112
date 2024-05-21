package com.test.dao;

import com.test.entity.UserMessage;

import java.util.List;

public interface UserMessageDao {
    /**
     * 查询user_MESSAGE表中满足条件的用户信息
     * @param userMessage 查询条件（用户姓名与用户性别）
     * @return 返回满足条件的用户信息
     * @throws Exception 用户查询失败
     */
    public abstract List<UserMessage> seletByNameandSex(UserMessage userMessage) throws Exception;
    public abstract int deleteById(Integer userId) throws Exception;
    public abstract int update(UserMessage userMessage,Integer userId) throws Exception;
}
