package com.hp.school.utils;

import java.sql.*;

public class JDBCUtil {
    private static Connection connection =null;
    private static PreparedStatement preparedStatement =null;
    private static ResultSet resultSet =null;
    static {
        //静态带代码块：在类加载的时候只执行这一次
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getThisConn(){
        return connection;
    }

    //获取连接
    public static Connection getConnection(){

        try {
            connection = DriverManager.getConnection( "jdbc:mysql://127.0.0.1:3306/school", "root", "root" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     *
     * @param sql   调用方法时传入的SQL语句
     * @param objects 调用方法时传入数组，数组元素用于SQL语句占位符填充值
     * @return
     * @throws SQLException
     */
    //查询通用的
    public static ResultSet query(String sql,Object[] objects) throws SQLException {
         getConnection();//获取连接
         preparedStatement = connection.prepareStatement( sql );
        if (objects != null) {
             /* 代码补充：请完成给SQL语句占位符填充参数的代码（10分）*/
                for (int i = 0; i < objects.length; i++) {
                    preparedStatement.setObject(1+i,objects[i]);
                }
        }
         resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    //通用增删改
    public  static int update(String sql,Object[] objects){
        getConnection();
        int count=0;
        try {
             preparedStatement = connection.prepareStatement( sql );
             if (objects != null) {
                for (int i = 0; i < objects.length; i++) {
                    preparedStatement.setObject( i+1,objects[i] );
                }
            }
            count= preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      
        return count;
    }
    //关闭资源
    public static void close(){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
