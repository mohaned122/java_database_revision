/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package revisionexamen;


import java.sql.*;

/**
 *
 * @author user
 */
public class RevisionExamen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //donne pour la connexion
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
            Statement stm = cx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);//si tu peut avoir plus de fonctionalite comme la parcour inverse
            //CRUD
            //read
            //lir le statement de façon normarle 
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                i++;
                System.out.println("etudiant "+i+"  id:"+rs.getInt(2)+"  cin:"+rs.getString(3)+"  nom:"+rs.getString(5)+"  prenom:"+rs.getString(6));
            }
            System.out.println("_______________________________");
            //lir le statement de façon inverse
            while(rs.previous()){
                
                System.out.println("etudiant "+i+"  id:"+rs.getInt(2)+"  cin:"+rs.getString(3)+"  nom:"+rs.getString(5)+"  prenom:"+rs.getString(6));
                i--;
            }
            System.out.println("****************************************************");
            //lir en evite les injection
            sql = "select * from student where id = ?";
            PreparedStatement pstmt = cx.prepareStatement(sql);
            pstmt.setInt(1, 3);
            rs = pstmt.executeQuery();
            while(rs.next()){
                i++;
                System.out.println("etudiant:  id:"+rs.getInt(2)+"  cin:"+rs.getString(3)+"  nom:"+rs.getString(5)+"  prenom:"+rs.getString(6));
            }
            //create,update,modify
            //methode one: ResultSet
            System.out.println("=========================================================================");
            Statement stm2 = cx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            sql = "select * from student where nom like 'z%'";
            ResultSet rs2 = stm2.executeQuery(sql);
            //mise a jour : je utilise ici le fonction absulte qui accede directement au row
            rs2.absolute(1);
            if(rs2.getString("prenom").equals("zayoudi")){
                rs2.updateString("prenom", "zayood");
                rs2.updateRow();
            }
            rs2.beforeFirst();
             while(rs2.next()){
                i++;
                System.out.println("etudiant:  id:"+rs2.getInt(2)+"  cin:"+rs2.getString(3)+"  nom:"+rs2.getString(5)+"  prenom:"+rs2.getString(6));
            }
             
            stm.close();
            cx.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("pilote panne");
        } catch (SQLException ex) {
            System.out.println("error de connection");
        }
    }
    
}
