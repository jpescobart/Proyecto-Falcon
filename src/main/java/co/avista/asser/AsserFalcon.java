package co.avista.asser;

import co.avista.models.services.legado.ObtenerRemanenteDesembolsoMasivoLegado;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

public class AsserFalcon {

    public void validarDesembolsoMasivoRemanenteLegado(List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerRemanenteDesembolsoMasivoLegadoListfinal,String numerocuenta){

        assertFalse(obtenerRemanenteDesembolsoMasivoLegadoListfinal.isEmpty());
        System.out.println("xxxxxxxxx"+obtenerRemanenteDesembolsoMasivoLegadoListfinal);
        assertEquals(obtenerRemanenteDesembolsoMasivoLegadoListfinal.get(0).getDocumentoIdentidad(),numerocuenta);
        assertEquals(obtenerRemanenteDesembolsoMasivoLegadoListfinal.get(0).getDocumentoTransaccionId(),"67");
        assertEquals(obtenerRemanenteDesembolsoMasivoLegadoListfinal.get(0).getNumeroCuenta(),numerocuenta);
        assertEquals(obtenerRemanenteDesembolsoMasivoLegadoListfinal.get(1).getDocumentoTransaccionId(),"75");
        assertEquals(obtenerRemanenteDesembolsoMasivoLegadoListfinal.get(2).getDocumentoTransaccionId(),"75");
        assertEquals(obtenerRemanenteDesembolsoMasivoLegadoListfinal.get(3).getDocumentoTransaccionId(),"67");

    }

    public void validarCortoyLargoPlazoCalculadoLegado(List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerRCortoyLargoPlazoLegadosList,List<String> numerocuenta) {
        numerocuenta = numerocuenta.stream().sorted().collect(Collectors.toList());
        obtenerRCortoyLargoPlazoLegadosList = obtenerRCortoyLargoPlazoLegadosList.stream().sorted(Comparator.comparingInt(ObtenerRemanenteDesembolsoMasivoLegado::getDocumentoIdentidadNumerico))
                .collect(Collectors.toList());

        assertFalse(obtenerRCortoyLargoPlazoLegadosList.isEmpty());
        assertFalse(numerocuenta.isEmpty());
        int valor = 0;
        for (String item : numerocuenta) {
            assertEquals(obtenerRCortoyLargoPlazoLegadosList.get(valor).getDocumentoIdentidad(), item);
            assertEquals(obtenerRCortoyLargoPlazoLegadosList.get(valor).getDocumentoTransaccionId(), "ZZ");
            assertTrue(obtenerRCortoyLargoPlazoLegadosList.get(valor).getConceptoCuenta().contains("CARTERA PROPIA LIBRANZA"));
            assertTrue(obtenerRCortoyLargoPlazoLegadosList.get(valor + 2).getConceptoCuenta().contains("CARTERA PROPIA LIBRANZA"));
            assertEquals(obtenerRCortoyLargoPlazoLegadosList.get(valor).getNumeroCuenta(), item);
            valor+=4;


       }
    }


    public void validarInteresDiariosCalculadosLegado(List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerInteresesDiariosLegadosList,List<String> numerocuenta,String tipoInteres) {
        numerocuenta = numerocuenta.stream().sorted().collect(Collectors.toList());
        obtenerInteresesDiariosLegadosList = obtenerInteresesDiariosLegadosList.stream().sorted(Comparator.comparingInt(ObtenerRemanenteDesembolsoMasivoLegado::getDocumentoIdentidadNumerico))
                .collect(Collectors.toList());

        assertFalse(obtenerInteresesDiariosLegadosList.isEmpty());
        assertFalse(numerocuenta.isEmpty());

        int valor = 0;
        for (String item : numerocuenta) {
            assertEquals(obtenerInteresesDiariosLegadosList.get(valor).getDocumentoIdentidad(), item);
            assertEquals(obtenerInteresesDiariosLegadosList.get(valor).getDocumentoTransaccionId(), tipoInteres);
            assertTrue(obtenerInteresesDiariosLegadosList.get(valor).getConceptoCuenta().contains("INTERES CORRIENTE LIBRANZA PUBLICA"));
            assertTrue(obtenerInteresesDiariosLegadosList.get(valor + 1).getConceptoCuenta().contains("INTERES CORRIENTE LIBRANZA PUBLICA"));
            assertEquals(obtenerInteresesDiariosLegadosList.get(valor).getNumeroCuenta(), item);
            valor+=2;


        }
    }

    public void validarInteresDiariosEnMoraCalculadosLegado(List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerInteresesDiariosLegadosList,List<String> numerocuenta,String tipoInteres) {
        numerocuenta = numerocuenta.stream().sorted().collect(Collectors.toList());
        obtenerInteresesDiariosLegadosList = obtenerInteresesDiariosLegadosList.stream().sorted(Comparator.comparingInt(ObtenerRemanenteDesembolsoMasivoLegado::getDocumentoIdentidadNumerico))
                .collect(Collectors.toList());

        assertFalse(obtenerInteresesDiariosLegadosList.isEmpty());
        assertFalse(numerocuenta.isEmpty());

        System.out.println("xxxxxx"+obtenerInteresesDiariosLegadosList);
        System.out.println("mmmmmmm"+numerocuenta);
        int valor = 0;
        for (String item : numerocuenta) {
            assertEquals(obtenerInteresesDiariosLegadosList.get(valor).getDocumentoIdentidad(), item);
            assertEquals(obtenerInteresesDiariosLegadosList.get(valor).getDocumentoTransaccionId(), tipoInteres);
            assertTrue(obtenerInteresesDiariosLegadosList.get(valor+2).getConceptoCuenta().contains("INTERES MORA LIBRANZA PUBLICA"));
            assertTrue(obtenerInteresesDiariosLegadosList.get(valor + 3).getConceptoCuenta().contains("INTERES MORA LIBRANZA PUBLICA"));
            assertEquals(obtenerInteresesDiariosLegadosList.get(valor).getNumeroCuenta(), item);
            valor+=4;


        }
    }

    public void validarEntradasContablesLegado(List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerEntradasContablesLegadoList,String numAccount) {


            assertFalse(obtenerEntradasContablesLegadoList.isEmpty());
            assertEquals(obtenerEntradasContablesLegadoList.get(0).getDocumentoIdentidad(), numAccount);
            assertEquals(obtenerEntradasContablesLegadoList.get(0).getDocumentoTransaccionId(), "75");
            assertTrue(obtenerEntradasContablesLegadoList.get(0).getConceptoCuenta().contains("RECUPERACION GMF"));
            assertTrue(obtenerEntradasContablesLegadoList.get(1).getConceptoCuenta().contains("SEGURO ANTICIPADO"));
            //assertTrue(obtenerEntradasContablesLegadoList.get(2).getConceptoCuenta().contains("TRANSITORIA"));
            assertTrue(obtenerEntradasContablesLegadoList.get(2).getConceptoCuenta().contains("CARTERA POR LEGALIZAR"));
            assertTrue(obtenerEntradasContablesLegadoList.get(3).getConceptoCuenta().contains("CARTERA PROPIA LIBRANZA PUBLICA CP"));
            assertTrue(obtenerEntradasContablesLegadoList.get(4).getConceptoCuenta().contains("CARTERA PROPIA LIBRANZA PUBLICA LP"));
            assertTrue(obtenerEntradasContablesLegadoList.get(5).getConceptoCuenta().contains("AVALES GARANTIAS COMUNITARIAS"));
            assertEquals(obtenerEntradasContablesLegadoList.get(0).getNumeroCuenta(),numAccount);

        }
}
