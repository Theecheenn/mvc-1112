package com.test.dao.impl;

import com.test.dao.UserMessageDao;
import com.test.entity.UserMessage;
import com.test.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserMessageDaoImpl implements UserMessageDao {

    @Override
    public List<UserMessage> seletByNameandSex(UserMessage userMessage) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count= 0;
        try{
            //加载数据库驱动，并获得数据库连接对象
            conn = DbUtil.getConnection();
            //创建基础的select语句
            StringBuilder sql = new StringBuilder("select user_id,user_name,user_password,user_phone,user_email,user_sex from user_message where 1=1");
            //根据查询语句拼接select语句
            if(userMessage.getUserName()!=null && !userMessage.getUserName().trim().equals("")){
                count+=1;
                sql.append(" and user_name like ?");
            }
            //判断查询条件中用户性别是否不为N
            if(userMessage.getUserSex()!=null && !userMessage.getUserSex().equalsIgnoreCase("n")){
                count+=2;
                sql.append(" and user_sex = ? ");
            }
            System.out.println(sql);
            ps = conn.prepareStatement(sql.toString());
            //替换准备语句对象中的问号
            if (count==1){
                ps.setString(1, userMessage.getUserName());
            }else if (count==2){
                ps.setString(2, userMessage.getUserSex());
            }else if (count==3){
                ps.setString(1, userMessage.getUserName());
                ps.setString(2, userMessage.getUserSex());
            }
            //使用准备语句对象执行select语句，并将查询的结果存入结果集中
            rs = ps.executeQuery();
            //在关闭结果集之前将结果集中的数据备份到list集合中
            //创建list集合，用于保存结果集中的所有数据
            List<UserMessage> list = new ArrayList<UserMessage>();
            UserMessage user = null;
            while(rs.next()){
                user = new UserMessage();
                //将结果集中当前行中的列，并将列中的数据添加到实体集的对象中
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setUserPassword(rs.getString("user_password"));
                user.setUserPhone(rs.getString("user_phone"));
                user.setUserEmail(rs.getString("user_email"));
                user.setUserSex(rs.getString("user_sex"));
                //将实体类的对象添加到List集合中
                list.add(user);
            }
            return list;
        }finally {
            DbUtil.close(rs,ps,conn);
        }
    }
}
