/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifier;

import java.util.StringTokenizer;

/**
 *
 * @author ALEXXX
 */
public class StringTokenizerTest {
    
    public static void main(String[] args) {
        String strToTest = "at / in on / to / with-from at-for * to*from";
        StringTokenizer st = new StringTokenizer(strToTest, " /-*");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }
    
}
