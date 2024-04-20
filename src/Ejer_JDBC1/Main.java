import modelo.Libro;
import modelo.LibroDAO;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
                                /** AÑADIR LIBROS **/
       /* System.out.println("************************AGREGAR LIBROS**********************");
        Libro a;
        Date fecha = new Date(); // TIENE QUE SER CON LA DE util NO CON LA DE sql
        a = new Libro(34310, "FSAFDSA", "PATO", "DONOS", fecha, 5, "TERROR");
        int columnasAfectadas = LibroDAO.create(a);
        if (columnasAfectadas == 1) {
            System.out.println("LIBRO AGREGADO CORRECTAMENTE :\n" + a);
        } else {
            System.out.println("LIBRO NO AGREGADO");
        }


        System.out.println("\n************************ VER LIBRO 132425 ************************");
        a = LibroDAO.obtener(132425);
        // COMPROBAR QUE NO SEA NULL
        if(a != null){ System.out.println(a); }*/


        System.out.println("\n********************* ACTUALIZAR LIBRO PATATAS **********************");
        Libro a;
        Date fecha = new Date(); // TIENE QUE SER CON LA DE util NO CON LA DE sql
        a = new Libro(34310, "FSAFDSA", "PATO", "DONOS", fecha, 5, "TERROR");
        a.setTitulo("SSSS");
        int colAfectadasActu = LibroDAO.actualizar(a);
        if (colAfectadasActu == 1) {
            System.out.println("LIBRO ACTUALIZADO CORRECTAMENTE :\n" + a);
        } else {
            System.out.println("LIBRO NO ACTUALIZADO");
        }



        /*System.out.println("\n********************* BORRAR LIBRO 132425 **********************");
        int colAfectadaDelete = LibroDAO.borrar(132425);
        if (colAfectadaDelete == 1) {
            System.out.println("LIBRO BORRADO CORRECTAMENTE :\n" + a);
        } else {
            System.out.println("LIBRO NO BORRADO");
        }


        System.out.println("\n********************* LIBROS **********************");
        List<Libro> listaLibros = LibroDAO.listar();
        // SI NO ESTA VACIO ES CUANDO LO RECORREMOS
        if(!listaLibros.isEmpty()){
            for(Libro libro : listaLibros){
                System.out.println("NOMBRE LIBRO : " + libro.getTitulo());
            }
        } else {
            System.out.println("NO HAY LIBROS");
        }*/

        System.out.println("\n***************** LIBROS CON EL MISMO NOMBRE*******************");
        List<Libro> listaLibros = LibroDAO.listarNombres("SSSSS");
        if(!listaLibros.isEmpty()){
            for(Libro libro : listaLibros){
                System.out.println("NOMBRE LIBRO : " + libro.getTitulo());
            }
        } else {
            System.out.println("NO HAY LIBROS");
        }



    }
}