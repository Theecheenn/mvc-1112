package com.test.service;

import com.test.entity.UserMessage;

import java.util.List;

/**
 * 用户模块业务逻辑层的接口，声明用户模块业务逻辑的方法
 */
public interface UserMessageService {
    /**
     *
     * @param userName 用户姓名
     * @param userSex  用户性别
     * @return 返回满足条件的用户信息
     * @throws Exception 用户查询失败
     * @author Thecchen
     */
    public abstract List<UserMessage> selectUser(String userName, String userSex) throws Exception;
}
