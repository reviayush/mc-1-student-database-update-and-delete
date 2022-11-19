package com.jap;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class DeleteUpdateStudentData
{
    static Connection connection= null;
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //Connection connection= null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/school";
        String user = "root";
        String password = "password@123";
        connection = DriverManager.getConnection(url, user, password);


        return connection;

    }

    public Student deleteStudentData(int studid) throws SQLException, ClassNotFoundException {
      // call getConnection() method
        Student stud= null;
        Connection connection  = DeleteUpdateStudentData.getConnection();
        String sql = "select * from student where studid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,studid );
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int studentid = resultSet.getInt(1);
            stud = new Student(studid);

        }

        return stud;

        //execute delete query
    }
    //Test
    public int updateStudentData(int studid) throws SQLException, ClassNotFoundException {
        // call getConnection() method
        int studen =0;
        Connection connection = DeleteUpdateStudentData.getConnection();
        String sql = "update student set marks=? where studid=?";//preparing a query with host variable or argument or parameter
        //parameters having index or position starting from 1
        //declare PreparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //set values for parameters
        //setXXX method of prepared statement
        preparedStatement.setInt(1, 300);
        preparedStatement.setInt(2,studid);

        //execute query
        studen = preparedStatement.executeUpdate();

        return studen;
        //execute update query
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DeleteUpdateStudentData salesDataDemo = new DeleteUpdateStudentData();
        salesDataDemo.deleteStudentData(10);
        salesDataDemo.updateStudentData(1);
    }
}
