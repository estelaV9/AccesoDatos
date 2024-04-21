import java.sql.*;
import java.util.Scanner;

/*EJEMPLO INSTITUTO MOSTRAR ALUMNOS NOTA CORTE
MOSTRAR TODOS LOS ALUMNOS DE UN CURSO CUYO NOTA ES MAYOR QUE CIERTA NOTA DE CORTE.
TANTO EL CURSO COMO LA NOTA DE CORTE SERA INTRODUCIDO POR EL USUARIO*/
public class Pg52_notaMayor {
    public static void main(String[] args) throws SQLException {
        Scanner reader = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/Instituto";
        Connection connection = DriverManager.getConnection(url, "root", "toor");
        System.out.println("¿De qué curso quieres mostrar los alumnos?");
        String curso = reader.next();
        System.out.println("¿A partir de qué nota quieres mostrar los alumnos?");
        double nota = reader.nextDouble();

        String sqlQuery = "SELECT nombre, media FROM alumnos WHERE curso = ? AND media > ?";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, curso);
        statement.setDouble(2, nota);
        ResultSet resultSet = statement.executeQuery();

        // RECORRER EL BUCLE PARA MOSTRAR LOS DATOS DE ESA CONSULTA
        System.out.println("*************ALUMNOS DE " + curso + " CON MAS DE UN " + nota + "**************");
        while (resultSet.next()) {
            System.out.println("Nombre del alumno : " + resultSet.getString(1)
                    + " con una media de " + resultSet.getString("media")
                    + "\n----------------------------------------------------------");

        }
    }
}
