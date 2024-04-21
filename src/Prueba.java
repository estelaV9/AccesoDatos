import java.sql.*;
import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/instituto";
        String username = "root";
        String password = "IESRibera23";
        int opcion;

        Connection connection = DriverManager.getConnection(url, username, password);
        Scanner sc = new Scanner(System.in);


        PreparedStatement statement = connection.prepareStatement("SELECT COUNT(NUM) FROM ALUMNOS;");
        ResultSet resultado = statement.executeQuery();
        resultado.next();
        int numAlumnosAntes = Integer.parseInt(resultado.getString(1));
        int numAlumnosUpdate = numAlumnosAntes;
        do {
            System.out.println("Hay actualmente " + numAlumnosUpdate + ", " + (numAlumnosUpdate-numAlumnosAntes) + " metidos a mano.");
            System.out.println("Quieres meter más? (0 para no, cualquier otro número para sí");
            opcion = Integer.parseInt(sc.nextLine());
            if (opcion!=0){
                numAlumnosUpdate++;
                System.out.println("Escribe el nombre");
                String nombre = sc.nextLine();
                System.out.println("Escribe la fecha de nacimiento (DD/MM/YYYY)");
                String fechaNac = sc.nextLine();
                System.out.println("Escribe la media");
                double media = Double.parseDouble(sc.nextLine());
                System.out.println("Escribe la clase");
                String clase = sc.nextLine();

                String sqlInsert = ("INSERT INTO ALUMNOS VALUES(?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?)");
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);

                preparedStatement.setInt(1, numAlumnosUpdate);
                preparedStatement.setString(2, nombre);
                preparedStatement.setString(3, fechaNac);
                preparedStatement.setDouble(4, media);
                preparedStatement.setString(5, clase);

                int num = preparedStatement.executeUpdate();
                System.out.println(num);
            }
        } while (opcion != 0);
        System.out.println("Hay actualmente " + numAlumnosUpdate);

        statement = connection.prepareStatement("SELECT * FROM ALUMNOS WHERE NUM BETWEEN ? AND ?;");
        statement.setInt(1, numAlumnosAntes+1);
        statement.setInt(2, numAlumnosUpdate);

        resultado = statement.executeQuery();
        System.out.println("Alumnos metidos: ");
        while (resultado.next()){
            System.out.println("-------------");
            System.out.println("ID del alumno: " + resultado.getInt(1));
            System.out.println("Nombre del alumno: " + resultado.getString(2));
            System.out.println("Fecha de nacimiento del alumno: " + resultado.getString(3));
            System.out.println("Media del alumno: " + resultado.getDouble(4));
            System.out.println("Curso del alumno: " + resultado.getString(5));
            System.out.println("------------\n");
        }
    }
}
