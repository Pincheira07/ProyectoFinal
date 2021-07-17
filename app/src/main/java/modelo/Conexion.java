package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String urlDB = "jdbc:mysql://127.0.0.1:3306/catalyst_db";
    private static final String user = "root";
    private static final String pass = "";




    public int contar() {
        int cuenta = 0;
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(urlDB, user, pass);

            PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM datos where activo = 1");
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                cuenta += rs.getInt(1);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        return cuenta;

    }



}
