package co.avista.utils;

import co.avista.logs.Log;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


public class AssertionsManager {
    private AssertionsManager() {
        throw new IllegalStateException("Utility class");
    }

    private static SoftAssertions softly;
    private static String msjLoger;
    public static void initAsserts(){
        softly = new SoftAssertions();
    }

    public static void hardAssertEqual(Object expected, Object obtained, String msj){
        msjLoger=String.format("Comparacion '%s': Dato esperado '%s' - Dato obtenido '%s'"
                ,msj ,expected.toString() , obtained.toString());
        Log.LOGGER.info(msjLoger);
        assertThat(obtained).as(msj).isEqualTo(expected);
    }

    public static void softAssertEqual(Object expected, Object obtained, String msj){
        msjLoger=String.format("Comparacion '%s': Dato esperado '%s' - Dato obtenido '%s'"
                ,msj ,expected.toString() , obtained.toString());
        Log.LOGGER.info(msjLoger);
        softly.assertThat(obtained).as(msj).isEqualTo(expected);
    }

    public static void softAssertNotEqual(Object expected, Object obtained, String msj){
        msjLoger=String.format("Comparacion de no iguales '%s': Dato esperado '%s' - Dato obtenido '%s'"
                ,msj ,expected.toString() , obtained.toString());
        Log.LOGGER.info(msjLoger);
        softly.assertThat(obtained).as(msj).isNotEqualTo(expected);
    }

    public static void softAssertContains(String origin, String contained, String msj){
        msjLoger=String.format("Dato contenido '%s': Dato a buscar '%s' - String origen '%s'"
                ,msj ,contained , origin);
        Log.LOGGER.info(msjLoger);
        softly.assertThat(origin.contains(contained)).as(msj).isEqualTo(true);
    }

    public static void softAssertIsNull(Object obtained, String msj){
        msjLoger=String.format("Verificacion que objeto es null '%s'",msj);
        Log.LOGGER.info(msjLoger);
        softly.assertThat(obtained).as(msj).isNull();
    }

    public static void softAssertNotNull(Object obtained, String msj){
        msjLoger=String.format("Verificacion que objeto no es null '%s'",msj);
        Log.LOGGER.info(msjLoger);
        softly.assertThat(obtained).as(msj).isNotNull();
    }

    public static void assertAll(){
        List<AssertionError> assertionErrors = softly.assertionErrorsCollected();
        if(!assertionErrors.isEmpty()) {
            msjLoger = String.format("se encontraron '%s' errores",assertionErrors.size());
            Log.LOGGER.info(msjLoger);
            for (AssertionError error : assertionErrors) {
                Log.LOGGER.info(error.getMessage());
            }
        }
        softly.assertAll();
    }

    public static void failTest(String msj){
        msjLoger="Se presento un problema en la ejecucion: ".concat(msj);
        Log.LOGGER.info(msjLoger);
        fail(msj);
    }

}
