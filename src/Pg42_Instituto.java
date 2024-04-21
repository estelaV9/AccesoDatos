/*A PARTIR DE LA DB CREADA CON EL ARCHIVO .sql.
INCREMENTAR LA NOTA MEDIA EN UN PUNTO A TODOS LOS ALUMNOS DEL CURSO "1B".
USAR UNA CONSULTA SQL QUE HAGA USO DE UPDATE.*/
import java.sql.*;

public class Pg42_Instituto {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Instituto";
        Connection con = DriverManager.getConnection(url, "root", "toor");

            // ACTUALIZAR LOS ALUMNOS QUE SEAN DEL 1B
        Statement statementUpdate = con.createStatement();
        String sqlUpdate = "UPDATE alumnos SET media = media + 1 WHERE curso = '1B'";
        int filasAfectadas = statementUpdate.executeUpdate(sqlUpdate);
        System.out.println("Se ha modificado : " + filasAfectadas + " filas\n");

            // VISUALIZAR TODOS LOS ALUMNOS
        Statement statementSelect = con.createStatement();
        String sqlSelect = "SELECT * FROM alumnos";
        ResultSet resultSet = statementSelect.executeQuery(sqlSelect);
        System.out.println("***********ALUMNOS*********\n");

        // SE RECORRE EL RESULTSET Y SE MUESTRA LA INFORMACION
        while (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            String curso = resultSet.getString("curso");
            Double media = resultSet.getDouble("media");
            System.out.println("Nombre: " + nombre + ", Curso: " + curso + ", Media: " + media);
        }

        resultSet.close(); // CERRAR EL RESULTSET
        statementSelect.close(); // CERRAR EL STATEMENT
        con.close(); // CERRAR LA CONEXION
    }
}
