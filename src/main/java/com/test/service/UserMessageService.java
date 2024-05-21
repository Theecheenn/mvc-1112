package com.test.service;

import com.test.entity.UserMessage;

import java.util.List;

/**
 * 用户模块业务逻辑层的接口，声明用户模块业务逻辑的方法
 */
public interface UserMessageService {
    /**
     * 查询满足条件的用户信息
     * @param userName 用户姓名
     * @param userSex  用户性别
     * @return 返回满足条件的用户信息
     * @throws Exception 用户查询失败
     * @author Thecchen
     */
    public abstract List<UserMessage> selectUser(String userName, String userSex) throws Exception;

    /**
     * 删除指定编号的用户信息
     * @param userId 用户编号
     * @return 删除成功返回大于0的整数，否则返回0
     * @throws Exception 删除失败
     * @author Thecchen
     */
    public abstract int delete(String userId)  throws Exception;

    /**
     * 修改指定编号的用户信息
     * @param userId 用户编号
     * @return 修改成功返回大于0的整数，否则返回0
     * @throws Exception 修改失败
     * @author Thecchen
     */
    public abstract int modifyUserMessage(Integer userId,String userPassword,String userName,String userPhone,String userEmail,String userSex) throws Exception;
}
