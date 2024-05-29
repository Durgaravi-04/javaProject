package tourism;

import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    public Conn(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql:///tms","root","21x42");
            s=c.createStatement();
        }catch(Exception e){

        }
    }
}

