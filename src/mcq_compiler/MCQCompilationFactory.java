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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import read.Question;
import tagger.TagType;

/**
 *
 * @author ALEXXX
 */
public class MCQCompilationFactory {
    
    
    public List<Question> getSpecifiedQList(TagType tagType) {
        List<Question> qSpecifiedList = new ArrayList<>();
        return qSpecifiedList;        
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
