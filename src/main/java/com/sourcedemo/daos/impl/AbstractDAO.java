package com.sourcedemo.daos.impl;

import com.sourcedemo.daos.GenericDAO;
import com.sourcedemo.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> implements GenericDAO<T> {

    // create a connection to database
    public Connection getConnection(){


        try {
            Class.forName("com.mysql.jdbc.Driver");
            String DB_URL = "jdbc:mysql://localhost:3306/demo";
            String USER_NAME = "root";
            String PASSWORD = "123456";
            return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
        }

    }

    private void setParameters (PreparedStatement statement, Object... parameters){

        try {
        for(int i = 0;  i< parameters.length; i++){

            Object parameter = parameters[i];
            int index = i +1;

            if(parameter instanceof  Long){
                statement.setLong(index,(Long) parameter);
            }
            else if(parameter  instanceof  String){
                statement.setString(index, (String) parameter);
            }
            else if(parameter instanceof  Integer){
                statement.setInt(index, (Integer) parameter);
            }
            else if(parameter instanceof  Timestamp){
                statement.setTimestamp(index, (Timestamp) parameter);
            }
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
    @Override
    public List<T> query(String query, RowMapper<T>  rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(query);
            setParameters(statement, parameters);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        }
        catch(SQLException e){

            throw new RuntimeException(e);
        }
        finally {

            try {

                if (connection != null) {
                    connection.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                return null;
            }
        }
    }

    @Override
    public Long insert(String query, Object... parameters) {

        ResultSet resultSet = null;
        Long newUserId  = null;
        Connection connection = null;
        PreparedStatement statement = null;


        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setParameters(statement, parameters);
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                newUserId = resultSet.getLong(1);
            }

            connection.commit();
            return newUserId;

        } catch (SQLException e) {
            if (connection != null) {
                //rollback:  when any error occurs, whatever command execute will be destroyed.
                try {

                    connection.rollback();

                } catch (SQLException err) {
                    err.printStackTrace();
                }
            }

            e.printStackTrace();
            return null;
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public void update(String query, Object... parameters) {

        Connection connection = null;
        PreparedStatement statement = null;

        try{

            connection = getConnection();
            connection.setAutoCommit(false);
            statement = getConnection().prepareStatement(query);
            setParameters(statement, parameters);
            statement.executeUpdate();
            connection.commit();
        }
        catch(SQLException e){
            if (connection != null) {

                try {

                    connection.rollback();

                } catch (SQLException err) {
                    err.printStackTrace();
                }
            }

            e.printStackTrace();
        }
        finally {

            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

    }



}
