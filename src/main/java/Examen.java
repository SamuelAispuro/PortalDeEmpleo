import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Examen {
    public static void main(String[] args) throws ParseException {
        //Random numAleatorio = new Random(234);
        //int n = numAleatorio.nextInt(100-2+1)+25;*
        ArrayList<Integer> numeros = new ArrayList<>();
         Integer menor;
        Integer n = 0;
        for (int i = 1; i <= 5; i++){
            n = (int) (Math.floor(Math.random() * (100 - 1 + 1)) + 1);

            numeros.add(n);
            }
        menor = numeros.get(0);
        for(Integer num:numeros){
            if(num < menor){
                menor = num;
            }
        }
        System.out.println(numeros);
        System.out.println("numero menor: "+menor);


        Date fecha1;
        Date fecha2;
        Date fecha3;



        String fecha11 = "23/01/2000";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date fecha1Formateada = formato.parse(fecha11);
        System.out.println(fecha1Formateada);






        Scanner teclado = new Scanner(System.in);
        ArrayList listaNombres = new ArrayList<>();

    String nombre;
        for(int i=1; i<=3; i++){
            System.out.println("ingresa un nombre.");
            nombre = teclado.nextLine();
            listaNombres.add(nombre);
        }


        System.out.println(listaNombres);
        Collections.sort(listaNombres);

        //listaNombres.sort(listaNombres);
        System.out.println("Ordenados: "+ listaNombres);





    }
}
