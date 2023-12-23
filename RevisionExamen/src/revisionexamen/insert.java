/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package revisionexamen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class insert {
    public static int j = 0;
    public static void main(String args[]){
        try{
            j++;
            int i = 0;
            String url = "jdbc:mysql://localhost:3306/exam";
            String user = "root";
            String pass = "root";
            // chargement de driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //etablier la connexion entre le base et le code 
            Connection cx = DriverManager.getConnection(url,user,pass);
            //create statement 
            String sql = "select * from student";
            Statement stm = cx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);//si tu peut avoir plus de fonctionalite comme la parcour inverse
            //CRUD
            ResultSet rs = stm.executeQuery(sql);
            //lire
            while(rs.next()){
                i++;
                System.out.println("etudiant "+i+"  id:"+rs.getInt(2)+"  cin:"+rs.getString(3)+"  nom:"+rs.getString(5)+"  prenom:"+rs.getString(6));
            }
            System.out.println("*****************************************************************");
            //ajoute
            rs.moveToInsertRow();
            rs.updateInt("id", 2005);
            rs.updateString("cin", "124330650");
            rs.updateString("nom","abodi");
            rs.updateString("prenom","bilhssona");
            rs.insertRow();
            rs.moveToCurrentRow();
            
            //lire resultset
            i=0;
            rs.beforeFirst();
            while(rs.next()){
                i++;
                System.out.println("etudiant "+i+"  id:"+rs.getInt(2)+"  cin:"+rs.getString(3)+"  nom:"+rs.getString(5)+"  prenom:"+rs.getString(6));
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("pilote panne");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
