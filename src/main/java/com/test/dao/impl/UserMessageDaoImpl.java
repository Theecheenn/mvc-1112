package com.test.dao.impl;

import com.test.dao.UserMessageDao;
import com.test.entity.UserMessage;
import com.test.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMessageDaoImpl implements UserMessageDao {
    /**
     * 查询USER_MESSAGE表中指定USER_NAME和USER_SEX的用户信息
     * @param userMessage 需要查询的用户信息
     * @return 查询成功返回List集合
     * @throws Exception 用户查询失败
     * @author Thecchen
     */
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

    /**
     * 删除USER_MESSAGE表中指定USER_ID的用户信息
     * @param userId 用户编号
     * @return 删除成功返回大于0的整数，否则返回0
     * @throws Exception 用户删除失败
     * @author Thecchen
     */
    @Override
    public int deleteById(Integer userId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DbUtil.getConnection();
            String sql = "delete from user_message where user_id=?";
            ps =conn.prepareStatement(sql);
            ps.setInt(1, userId);
            int rows = ps.executeUpdate();
            if(rows>0){
                conn.commit();
                return rows;
            }
            conn.rollback();

        }finally {
            DbUtil.close(null,ps,conn);
        }
        return 0;
    }

    /**
     * 修改USER_MESSAGE表中指定USER_ID的用户信息
     * @param userMessage 需要修改的用户信息
     * @return 修改成功返回大于0的整数，否则返回0
     * @throws Exception 用户修改失败
     * @author Thecchen
     */
    @Override
    public int update(UserMessage userMessage,Integer user_id) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DbUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "update user_message set user_name =?,user_password=?," +
                    "user_phone=?,user_email=?,user_sex=? where user_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userMessage.getUserName());
            ps.setString(2, userMessage.getUserPassword());
            ps.setString(3, userMessage.getUserPhone());
            ps.setString(4, userMessage.getUserEmail());
            ps.setString(5,userMessage.getUserSex());
            ps.setInt(6, user_id);
            System.out.println("1");
            int rows = ps.executeUpdate();
            if(rows>0){
                conn.commit();
                return rows;
            }
            conn.rollback();
        }catch (ClassNotFoundException e){
            System.out.println("数据库驱动加载失败");
        }catch (SQLException e){
            System.out.println("数据库异常");
            e.printStackTrace();
        }finally {
            DbUtil.close(null,ps,conn);
        }
        return 0;
    }
}
