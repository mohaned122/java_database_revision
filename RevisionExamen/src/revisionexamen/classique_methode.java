/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package revisionexamen;
import java.sql.*;

/**
 *
 * @author user
 */
public class classique_methode {
    public static void main(String arg[]){
        try{
            System.out.println("******************classique methode*****************");
            String url = "jdbc:mysql://localhost:3306/exam";
            String user = "root";
            String pass = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cx = DriverManager.getConnection(url,user,pass);
            
            //crud string
            String sqlC = "insert into student(id,nom,prenom,cin) values(1200,'mohammadin','ka3bora','123504893')";
            String sqlR = "select * from student";
            String sqlRS = "select * from student where prenom = 'zayoud'";
            String sqlU = "update student set nom = 'mohanid' where nom like 'mohanned'";
            String sqlD = "delete from student where id = 1200";
            
            //create statement
            Statement stm = cx.createStatement();
            
            //read all: sqlR or read one: sqlRS
            ResultSet rs = stm.executeQuery(sqlR);
            while(rs.next()){
                System.out.println("etudiant   id:"+rs.getInt(2)+"  cin:"+rs.getString(3)+"  nom:"+rs.getString(5)+"  prenom:"+rs.getString(6));
            }
            /*insert 
            int ok = stm.executeUpdate(sqlC);
            if(ok == 1){
                System.out.println("etudiant bien insert");
            }else{
                System.out.println("error");
            }*/
            
            /*update
            int ok = stm.executeUpdate(sqlU);
            if(ok >= 1){
                System.out.println("etudiant bien modify");
            }else{
                System.out.println("error or nothing change happen");
            }*/
            
            //delete
            int ok = stm.executeUpdate(sqlD);
            if(ok >= 1){
                System.out.println("etudiant bien delete");
            }else{
                System.out.println("error or nothing change happen");
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
}
