/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcq_compiler;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import config.PropertiesLoader;
import question.Question;
import question.topics.TagType;

/**
 *
 * @author ALEXXX
 */
public class MCQCompilationFactory {
    
    
    public static List<Question> getSpecifiedQList(TagType tagType) {
        List<Question> qSpecifiedList = new ArrayList<>();
        
        String currWorkDir = new File("").getAbsolutePath();

        // TODO put all database connection properties in a file or class Properties
        Properties properties = PropertiesLoader.getProperties();
        String allElemDbPath = properties.getProperty("allElemDbPath");
        String allElemDBAbsolutePath = currWorkDir + allElemDbPath;

        String tableName = properties.getProperty("allElemTableName");
        String urlSQLite = "jdbc:sqlite:" + allElemDBAbsolutePath.replace("\\", "/");
        
        // int rowsNum = getNumOfRows(tagType);
        
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs;
        
        String tagString = tagType.getTag();
        
        try {            
            // load driver
            String dBDriver = properties.getProperty("dBDriver");
            Class.forName(dBDriver);
            connection = DriverManager.getConnection(urlSQLite);
            
                        
            stmt = connection.createStatement();
            String sqlCommand = "select * from all_elem "
                    + "where tags = '" + tagString + "'";
            
            rs = stmt.executeQuery(sqlCommand);
            
            int rowNum = 0;
            
            while (rs.next()) {
                rowNum++;
                // stmt.getString(1, i);                

                Question question = new Question();
                question.setId(rowNum);

                String questionText = rs.getString("question");
                question.setQuestionPart(questionText);

                String aOption = rs.getString("a");
                question.setOptionA(aOption);

                String bOption = rs.getString("b");
                question.setOptionB(bOption);

                String cOption = rs.getString("c");
                question.setOptionC(cOption);

                String dOption = rs.getString("d");
                question.setOptionD(dOption);

                String right = rs.getString("right");
                question.setRightAns(right);

                String tags = rs.getString("tags");
                question.setTags(tags);

                qSpecifiedList.add(question);

            } // end of while loop
            
        } catch (Exception e) {
            // Handle errors for class.forName
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return qSpecifiedList;        
    }

    public static List<Question> readAllQuestions() {
        List<Question> allQuestions = new ArrayList<>();

        String currWorkDir = new File("").getAbsolutePath();

        // TODO put all database connection properties in a file or class Properties
        String pathToAllElemDB = currWorkDir + "\\resources\\ALL_ELEM.db";
        String tableName = "all_elem";
        String urlSQLite = "jdbc:sqlite:" + pathToAllElemDB.replace("\\", "/");

        Connection connection;
        Statement stmt = null;
        ResultSet rs;

        try {
            // load driver
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(urlSQLite);


            stmt = connection.createStatement();
            String sqlCommand = "select * from all_elem";
            rs = stmt.executeQuery(sqlCommand);

            int rowNum = 0;
            while (rs.next()) {
                rowNum++;
                Question question = new Question();
                question.setId(rowNum);

                String questionText = rs.getString("question");
                question.setQuestionPart(questionText);

                String aOption = rs.getString("a");
                question.setOptionA(aOption);

                String bOption = rs.getString("b");
                question.setOptionB(bOption);

                String cOption = rs.getString("c");
                question.setOptionC(cOption);

                String dOption = rs.getString("d");
                question.setOptionD(dOption);

                String right = rs.getString("right");
                question.setRightAns(right);

                String tags = rs.getString("tags");
                question.setTags(tags);

                allQuestions.add(question);

            } // end of while loop

        } catch (Exception e) {
            // Handle errors for class.forName
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return allQuestions;
    }
    
    public static int getNumOfRows(TagType tagType) {
        String currWorkDir = new File("").getAbsolutePath();
        String pathToAllElemDB = currWorkDir + "\\resources\\ALL_ELEM.db";
        
        String tableName = "all_elem";
        
        String urlSQLite = "jdbc:sqlite:" + pathToAllElemDB.replace("\\", "/");
        
        int numOfRows = 0;
        
        Connection connection = null;
        Statement stmt;
        ResultSet rs;
        
        String tag = tagType.getTag();
        
        try {
            // load driver
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(urlSQLite);
            stmt = connection.createStatement();
            
            String sql = "select count(*) from all_elem "
                    + "where tags = '" + tag + "'";
            
            rs = stmt.executeQuery(sql);
            
            rs.next();
            numOfRows = rs.getInt(1);

            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return numOfRows;
    }
    
}
