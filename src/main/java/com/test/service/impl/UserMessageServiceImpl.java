package com.test.service.impl;


import com.test.dao.UserMessageDao;
import com.test.dao.impl.UserMessageDaoImpl;
import com.test.entity.UserMessage;
import com.test.service.UserMessageService;

import java.util.List;

/**
 *用户模块业务逻辑层接口的实现类，实现用户模块业务逻辑的方法
 */
public class UserMessageServiceImpl implements UserMessageService {
    //创建数据持久化层的对象
    private UserMessageDao dao = new UserMessageDaoImpl();

    @Override
    public List<UserMessage> selectUser(String userName, String userSex) throws Exception {
        if(userName != null && !userName.trim().equals("")){
            userName = "%"+userName+"%";
        }
        //将用户姓名和用户性别封装到UserMessage实体类的对象中
        UserMessage userMessage = new UserMessage();
        userMessage.setUserName(userName);
        userMessage.setUserSex(userSex);

        //调用UserMessageDao中的selectByNameAndSex()方法，查询满足条件的用户信息，并获得查询结果
        List<UserMessage> list = dao.seletByNameandSex(userMessage);
        return list;
    }

    @Override
    public int delete(String userId) throws Exception {
     //判断userId是否为null
     if(userId == null){
         return 0;
     }
     //调用UserMessageDao中的deleteById()方法，删除指定编号的用户信息
     return this.dao.deleteById(Integer.valueOf(userId));
    }

    @Override
    public int modifyUserMessage(Integer userId,String userName,String userPassword,String userPhone,String userEmail,String userSex) throws Exception {
        UserMessage  userMessage  = new UserMessage();
        userMessage.setUserId(userId);
        userMessage.setUserName(userName);
        userMessage.setUserPassword(userPassword);
        userMessage.setUserPhone(userPhone);
        userMessage.setUserEmail(userEmail);
        userMessage.setUserSex(userSex);
        return this.dao.update(userMessage,Integer.valueOf(userId));
    }
}
