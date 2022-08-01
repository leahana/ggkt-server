package com.atguigu.ggkt.service_vod_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: LeahAna
 * @Date: 2022/7/8 12:20
 * @Desc: 测试类
 */

public class SQLConnectionTest {
        public static void main(String[] args) {
            // url = "jdbc:mysql://localhost:3306/数据库名"
            String url = "jdbc:mysql://localhost:3306/glkt_vod?characterEncoding=utf-8&useSSL=false";
            String user = "root";
            String pwd = "r00t123!@#";
            // jdbc驱动
            String Driver = "com.mysql.cj.jdbc.Driver";

            try {
                // 注册jdbc驱动
                Class.forName(Driver);
                Connection connection = DriverManager.getConnection(url,user,pwd);
                if(!connection.isClosed()){
                    System.out.println("数据库连接成功！");
                }
                connection.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("数据库驱动未找到！");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("数据库连接失败！");
            }
        }
    }


