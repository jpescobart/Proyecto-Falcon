package co.avista.utils;

import co.avista.logs.Log;
import com.github.javafaker.Faker;

import java.io.*;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GeneralUtilities {

    public GeneralUtilities() {
        //throw new IllegalStateException("Utility class");
    }

    public static String uuidGenerator() {
        return String.valueOf(UUID.randomUUID());
    }

    public static String bearerTokenGenerator() {
        SecureRandom secureRandom = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public static String currentDateGenerator(String dateFormat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }


    public static void createDirectory(String path) {
        new File(path).mkdir();
        path = "ubicacion del directorio creado: ".concat(path);
        Log.LOGGER.info(path);
    }

    public static void deleteFile(String path) {
        File file = new File(path);
        path = "ubicacion del archivo a eliminar: ".concat(path);
        Log.LOGGER.info(path);
        if (file.exists()) {
            if (file.delete()) {
                Log.LOGGER.info("No se pudo eliminar el archivo");
            } else {
                Log.LOGGER.info("archivo eliminado");
            }
        } else {
            Log.LOGGER.info("no se encontro el archivo");
        }
    }

    public static int randomNumberGeneratorByLength(int length) {
        int maxNum = (int) Math.pow(10, length) - 1;
        new String(new char[3]).replace("\0", "#");
        return randomNumberGenerator(maxNum);
    }

    public static int randomNumberGenerator(int maxNum) {
        Random rand = new SecureRandom();
        return rand.nextInt(maxNum);
    }

    public static String generateCurrentDate(String dateFormat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String GenerateNameRandom() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        return firstName;
    }

    public static String GenerateLastNameRandom() {
        Faker faker = new Faker();
        String middleName = faker.name().firstName();
        return middleName;
    }

    public static String GenerateSurnameRandom() {
        Faker faker = new Faker();
        String firstSurName = faker.name().lastName();
        String[] parte1 = firstSurName.split(" ");
        String firstSurNamefinal = parte1[0];
        return firstSurNamefinal;
    }

    public static String GenerateNumAletori() {
        Faker faker = new Faker();
        int numFaker = faker.number().numberBetween(123456789,999999999);
        //String numfin = faker.number().digits(9);
        String numFakerFinal = String.valueOf(numFaker);
        return numFakerFinal;

    }

    public static String generateBase64() {
        String name = "juan.escobar";
        String pass = "Sofka2021*";
        String autentication = name + ":" + pass;
        String cadenaCodificada = Base64.getEncoder().encodeToString(autentication.getBytes());
        System.out.println("codificado: " + cadenaCodificada);

        return cadenaCodificada;
    }

    public static void generarTxt(String numAleatoriaccount,String nombreArchivo) {
        FileWriter escribir;
        PrintWriter linea;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String nameFile = ""+nombreArchivo+"" + simpleDateFormat.format(Calendar.getInstance().getTime()) + ".txt";
        String ruta = "src/test/resources/fileText/" + nameFile;
        File file = new File(ruta);
        if (!file.exists()) {
            try {
                file.createNewFile();
                escribir = new FileWriter(file, true);
                linea = new PrintWriter(escribir);
                linea.println(numAleatoriaccount + ",");
                linea.close();
                escribir.close();
            } catch (IOException e) {
                System.out.println("El archivo no se genero correctamente");
            }

        } else {

            try {
                escribir = new FileWriter(file, true);
                linea = new PrintWriter(escribir);
                linea.println(numAleatoriaccount + ",");
                linea.close();
                escribir.close();
            } catch (IOException e) {
                System.out.println("El archivo no se genero correctamente");
            }

        }

    }

    public static void generarTxtCausacionDeIntereses(String numAleatoriaccount, String tipoCartera) {
        FileWriter escribir;
        PrintWriter linea;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String nameFile = ""+tipoCartera+"" + simpleDateFormat.format(Calendar.getInstance().getTime()) + ".txt";
        String ruta = "src/test/resources/fileText/" + nameFile;
        File file = new File(ruta);
        if (!file.exists()) {
            try {
                file.createNewFile();
                escribir = new FileWriter(file, true);
                linea = new PrintWriter(escribir);
                linea.println(numAleatoriaccount + ",");
                linea.close();
                escribir.close();
            } catch (IOException e) {
                System.out.println("El archivo no se genero correctamente");
            }

        } else {

            try {
                escribir = new FileWriter(file, true);
                linea = new PrintWriter(escribir);
                linea.println(numAleatoriaccount + ",");
                linea.close();
                escribir.close();
            } catch (IOException e) {
                System.out.println("El archivo no se genero correctamente");
            }

        }

    }

    public static void clearFichero() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String nameFile = "fileAccount_" + simpleDateFormat.format(Calendar.getInstance().getTime()) + ".txt";
        String ruta = "src/test/resources/fileText/" + nameFile;
        File fichero = new File(ruta);
        if (fichero.delete())
            System.out.println("El fichero ha sido borrado satisfactoriamente");
        else
            System.out.println("El fichero no puede ser borrado");
    }

    public static String leerDatosAccountTxt(String nombreArchivo) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //String nameFile = ""+nombreArchivo+"" + simpleDateFormat.format(Calendar.getInstance().getTime()) + ".txt";
        String fechaanteriorfinal= previousdate();
        String nameFile = ""+nombreArchivo+"" +fechaanteriorfinal+  ".txt";
        String ruta = "src/test/resources/fileText/" + nameFile;
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String listaAccounts="";

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null){
                listaAccounts+=linea;
                System.out.println(linea);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return listaAccounts;
    }

    public static String previousdate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add (Calendar.DATE, -1); // Obtener el día anterior
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String fechaAnterior = df.format(date);
        return fechaAnterior;
    }

    public static String previousMonthsTwo(){
        Calendar calendar = Calendar.getInstance();
        calendar.add (Calendar.MONTH, -2); // Obtener el mes anterior
        System.out.println(calendar);
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("YYYY-MM");
        String fechaMesAnterior = df.format(date);
        return fechaMesAnterior;
    }

    public static String previousMonthsOne(){
        Calendar calendar = Calendar.getInstance();
        calendar.add (Calendar.MONTH, -1); // Obtener el mes anterior
        System.out.println(calendar);
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("YYYY-MM");
        String fechaMesAnterior = df.format(date);
        return fechaMesAnterior;
    }

    public static String leerDatosAccountInteresesDiariosTxt(String tipocartera) {
        String fechaanteriorfinal= previousdate();
        String nameFile = ""+tipocartera+"" +fechaanteriorfinal+  ".txt";
        String ruta = "src/test/resources/fileText/" + nameFile;
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String listaAccounts="";

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null){
                listaAccounts+=linea;
                System.out.println(linea);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return listaAccounts;
    }


}



