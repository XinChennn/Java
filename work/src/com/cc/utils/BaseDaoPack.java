package com.cc.utils;

import java.sql.*;

public class BaseDaoPack {
    /**
     * 封装JDBC工具类，复用代码
     */
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection()  {
        //获取连接对象，比如做事务
        return connection;
    }

    public ResultSet select(String sql,Object[] objects) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);  //这里不需要try..catch  因为不调用关闭资源方法
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i+1,objects[i]);
            }
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public int update(String sql,Object[] objects){
        int count=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (objects != null) {
                for (int i = 0; i < objects.length; i++) {
                    preparedStatement.setObject(i+1,objects[i]);
                }
            }
            count = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public void close(){
        //关闭资源方法
        if (resultSet!= null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (preparedStatement!= null) {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection!= null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
