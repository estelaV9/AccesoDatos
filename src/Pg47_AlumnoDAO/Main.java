import CLASES.Alumno;
import CLASES.AlumnoDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date; // NUNCA EN UN MAIN TIENE QUE TENER DE TIPO SQL, ESO SE ENCARGA LAS CLASES
import java.util.List;

public class Main {
    public static void main(String[] args) {
                            /** AÑADIR ALUMNOS **/
        Alumno a;
        Date fecha = new Date(); // TIENE QUE SER CON LA DE util NO CON LA DE sql
        a = new Alumno(30, "Pato", fecha, 4.9, "1A");

        int columnasAfectadas = AlumnoDAO.create(a);
        // ES PREFERIBLE QUE ALUMNODAO NO DEVUELVA MENSAJES
        System.out.println("\n************************AGREGAR ALUMNOS**********************");
        if (columnasAfectadas == 1) {
            System.out.println("ALUMNO AGREGADO CORRECTAMENTE :\n" + a);
        } else {
            System.out.println("ALUMNO NO AGREGADO");
        }

        /* OTRO EJEMPLO DE FECHAS ---> NO SABEMOS COMO PONER FECHAS
        String fecha2 = "23-04-2002";
        // LA FECHAS NO ENTRAN EN EL EXAMEN PORQUE NO LAS HEMOS TRABAJADO
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        a = new Alumno(25, "San Jacobo", Date.parse(fecha2), 0.1,"2B");*/

        System.out.println("\n************************ VER ALUMNO 30 ************************");
        a = AlumnoDAO.obtener(30);
        // COMPROBAR QUE NO SEA NULL
        if(a!= null){
            System.out.println(a);
        }

                                /** ACTUALIZAR ALUMNO **/
        a.setNotaMedia(10.0);
        int colAfectadasActu = AlumnoDAO.actualizar(a);
        System.out.println("\n********************* ACTUALIZAR ALUMNO 30 **********************");
        if (colAfectadasActu == 1) {
            System.out.println("ALUMNO ACTUALIZADO CORRECTAMENTE :\n" + a);
        } else {
            System.out.println("ALUMNO NO ACTUALIZADO");
        }


                                /** BORRAR ALUMNO **/
        int colAfectadaDelete = AlumnoDAO.borrar(30);
        System.out.println("\n********************* BORRAR ALUMNO 30 **********************");
        if (colAfectadaDelete == 1) {
            System.out.println("ALUMNO BORRADO CORRECTAMENTE :\n" + a);
        } else {
            System.out.println("ALUMNO NO BORRADO");
        }

                                /** LISTAR ALUMNOS **/
        List<Alumno> listaAlumnos = AlumnoDAO.listar();
        System.out.println("\n********************* ALUMNOS **********************");
        // SI NO ESTA VACIO ES CUANDO LO RECORREMOS
        if(!listaAlumnos.isEmpty()){
            for(Alumno alumno : listaAlumnos){
                System.out.println("Nombre : " + alumno.getNombre());
            }
        } else {
            System.out.println("NO HAY ALUMNOS");
        }

    }
}