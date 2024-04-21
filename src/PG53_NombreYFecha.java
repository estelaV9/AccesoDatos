/*EJERCICIO INSTITUTO MOSTRAR NOMBRE Y FECHA DE NACIMIENTO
Mostrar el nombre y la fecha de nacimiento de todos los alumnos
de un curso, que se solicitará al usuario por teclado.*/

import java.sql.*;
import java.util.Scanner;

public class PG53_NombreYFecha {
    public static void main(String[] args) throws SQLException {
        Scanner reader = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/instituto"; 
        String username = "root";
        String password = "IESRibera23";
        Connection connection = DriverManager.getConnection(url, username, password);

        boolean hayAlumnos = false; // SEMAFORO PARA SABER SI EL ALUMNO EXISTE O NO
        String sql = "SELECT nombre, fnac FROM alumnos WHERE nombre = ?";

        System.out.println("Introduzca los alumnos que quiera visualizar el nombre y fecha");
        String nombreAlumno = reader.nextLine();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nombreAlumno);
        ResultSet resultSetResulset = statement.executeQuery();


        System.out.println("************************ALUMNOS***********************");
        while (resultSetResulset.next()) {
            hayAlumnos = true;
            System.out.println("Nombre del alumno : " + resultSetResulset.getString(1)
                    + " nacido en el " + resultSetResulset.getString("fnac")
                    + "\n----------------------------------------------------------");
        }
        if (!hayAlumnos) {
            System.out.println("No hay alumnos con ese nombre");
        }
    }
}
