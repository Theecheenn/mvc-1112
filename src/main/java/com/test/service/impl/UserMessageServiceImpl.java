package com.test.service.impl;


import com.test.dao.UserMessageDao;
import com.test.dao.impl.UserMessageDaoImpl;
import com.test.entity.UserMessage;

import java.util.List;

/**
 *用户模块业务逻辑层接口的实现类，实现用户模块业务逻辑的方法
 */
public class UserMessageServiceImpl implements com.test.service.UserMessageService {
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
}
