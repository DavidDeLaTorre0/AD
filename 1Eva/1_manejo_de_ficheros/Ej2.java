import java.io.File;

public class Ej2 {
    public static void main(String[] args) {
        File directorio = new File(System.getProperty("user.dir"),"nuevoDirecto");

        System.out.println(System.getProperty("user.dir"));
    }
}
