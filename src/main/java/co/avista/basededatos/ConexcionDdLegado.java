package co.avista.basededatos;
import co.avista.models.services.legado.ObtenerRemanenteDesembolsoMasivoLegado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ConexcionDdLegado {

    private static Connection conn;
    private static final String driver = "oracle.jdbc.driver.OracleDriver";
    private static final String user = "JESCOBAR";
    private static final String password = "uFB3CBCAe26f8";
    private static final String url = "jdbc:oracle:thin:@192.168.0.15:1521:cscrac1";


    public ConexcionDdLegado() {
        conn = null;
        try {
            Class.forName(driver);
            //DriverManager.setLoginTimeout(10);
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("conexion establecidad");
            }
        } catch (Exception e) {
            System.out.println("Error de conexion");
        }
    }


    public Connection getConnection() {
        return conn;

    }

    public void desconectar() {
        conn = null;
        if (conn == null) {
            System.out.println("conexion terminada");
        }
    }


    public int consultarIdDesemboloMasivoLegado() {
        String query = " Select e.* " +
                " From ucscd.estructura_ws e " +
                " where e.TIPO_WS = 3 " +
                " order by ID desc";
        int idRegistrolegadoDesembolsoMasivo=0;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                idRegistrolegadoDesembolsoMasivo = rs.getInt("ID");
                System.out.println("el id es " + idRegistrolegadoDesembolsoMasivo);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idRegistrolegadoDesembolsoMasivo;
    }

    public List<ObtenerRemanenteDesembolsoMasivoLegado> consultarMovimientosDesembolsoMasivoLegado(int id) {
        List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerRemanenteDesembolsoMasivoLegadosList = new ArrayList<>();
        String query = " Select jt.* --, e.*\n" +
                " From ucscd.estructura_ws e ,\n" +
                "      JSON_TABLE(respuesta , '$[*]'\n" +
                "                 Columns (\"doc_ref\"   Varchar2 ( 17) Path '$.DOC_REF_2',\n" +
                "                 \"Documento\"   Varchar2 ( 17) Path '$.DOCUMENTO',\n" +
                "                 \"NUMERO\"   Varchar2 ( 17) Path '$.NUMERO',\n" +
                "                 \"CODIGO_PAIS\"   Varchar2 ( 17) Path '$.CODIGO_PAIS',\n" +
                "                 \"NIT_EMPRESA\"   Varchar2 ( 17) Path '$.NIT_EMPRESA',\n" +
                "                 \"FECHA_MOVIMIENTO\"   Varchar2 ( 17) Path '$.FECHA_MOVIMIENTO',\n" +
                "                 \"DESCRIPCION_GENERAL\"   Varchar2 ( 1000) Path '$.DESCRIPCION_GENERAL',\n" +
                "                 \"MOVIMIENTO_LOTE\"   Varchar2 ( 17) Path '$.MOVIMIENTO_LOTE',\n" +
                "                 \"VALOR_0\"   Varchar2 ( 17) Path '$.VALOR_0',\n" +
                "                 \"Cierre\"   Varchar2 ( 17) Path '$.Cierre',\n" +
                "                 \"Periodo\"   Varchar2 ( 17) Path '$.Periodo',\n" +
                "                 \"NRO_ITEM\"   Varchar2 ( 17) Path '$.NRO_ITEM',\n" +
                "                 \"CENTRO_UTILIDAD\"   Varchar2 ( 17) Path '$.CENTRO_UTILIDAD',\n" +
                "                 \"SUBCENTRO_UTILIDAD\"   Varchar2 ( 17) Path '$.SUBCENTRO_UTILIDAD',\n" +
                "                 \"CUENTA_CONTABLE\"   Varchar2 ( 17) Path '$.CUENTA_CONTABLE',\n" +
                "                 \"CENTRO_COSTO\"   Varchar2 ( 17) Path '$.CENTRO_COSTO',\n" +
                "                 \"SUBCENTRO_COSTO\"   Varchar2 ( 17) Path '$.SUBCENTRO_COSTO',\n" +
                "                 \"TERCERO\"   Varchar2 ( 17) Path '$.TERCERO',\n" +
                "                 \"CONCEPTO_CUENTA\"   Varchar2 ( 1000) Path '$.CONCEPTO_CUENTA',\n" +
                "                 \"DOC_REF_1\"   Varchar2 ( 17) Path '$.DOC_REF_1',\n" +
                "                 \"DOC_REF_2\"   Varchar2 ( 17) Path '$.DOC_REF_2',\n" +
                "                 \"TIPO_MVTO\"   Varchar2 ( 17) Path '$.TIPO_MVTO',\n" +
                "                 \"VALOR_MVTO\"   Varchar2 ( 17) Path '$.VALOR_MVTO',\n" +
                "                 \"VALOR_BASE\"   Varchar2 ( 17) Path '$.VALOR_BASE',\n" +
                "                 \"VALOR_1\"   Varchar2 ( 17) Path '$.VALOR_1',\n" +
                "                 \"VALOR_2\"   Varchar2 ( 17) Path '$.VALOR_2',\n" +
                "                 \"VALOR_3\"   Varchar2 ( 17) Path '$.VALOR_3'\n" +
                "                          ) ) jt\n" +
                "  Where    e.ID = " + id + "\n" +
                "  --doc_ref = '4247260203055'\n" +
                "  --and Documento = '75'\n" +
                "  order by ID desc";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ObtenerRemanenteDesembolsoMasivoLegado obtenerRemanenteDesembolsoMasivoLegado = new ObtenerRemanenteDesembolsoMasivoLegado();
                obtenerRemanenteDesembolsoMasivoLegado.setDocumentoIdentidad(rs.getString("DOC_REF"));
                obtenerRemanenteDesembolsoMasivoLegado.setDocumentoTransaccionId(rs.getString("DOCUMENTO"));
                obtenerRemanenteDesembolsoMasivoLegado.setConceptoCuenta(rs.getString("CONCEPTO_CUENTA"));
                obtenerRemanenteDesembolsoMasivoLegado.setNumeroCuenta(rs.getString("DOC_REF_2"));

                obtenerRemanenteDesembolsoMasivoLegadosList.add(obtenerRemanenteDesembolsoMasivoLegado);
                System.out.println("Los resultados son" + obtenerRemanenteDesembolsoMasivoLegadosList.toString());

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" no existen datos en la base de datos legado");
        }
        return obtenerRemanenteDesembolsoMasivoLegadosList;

    }



    public List<ObtenerRemanenteDesembolsoMasivoLegado> consultarMovimientosCortoyLargoPlazoLegado(List<String> numAccount) {
        List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerRCortoyLargoPlazoLegadosList = new ArrayList<>();
        String listaAccounts="";
        for (String item:numAccount) {
            if (listaAccounts.equals("")){
                listaAccounts+="'"+item+"'";
            }else {
                listaAccounts+=",'"+item+"'";
            }

        }
        String query = "  Select jt.* --, e.*\n" +
                " From ucscd.estructura_ws e ,\n" +
                "      JSON_TABLE(respuesta , '$[*]'\n" +
                "                 Columns (\"doc_ref\"   Varchar2 ( 17) Path '$.DOC_REF_2',\n" +
                "                 \"Documento\"   Varchar2 ( 17) Path '$.DOCUMENTO',\n" +
                "                 \"NUMERO\"   Varchar2 ( 17) Path '$.NUMERO',\n" +
                "                 \"CODIGO_PAIS\"   Varchar2 ( 17) Path '$.CODIGO_PAIS',\n" +
                "                 \"NIT_EMPRESA\"   Varchar2 ( 17) Path '$.NIT_EMPRESA',\n" +
                "                 \"FECHA_MOVIMIENTO\"   Varchar2 ( 17) Path '$.FECHA_MOVIMIENTO',\n" +
                "                 \"DESCRIPCION_GENERAL\"   Varchar2 ( 1000) Path '$.DESCRIPCION_GENERAL',\n" +
                "                 \"MOVIMIENTO_LOTE\"   Varchar2 ( 17) Path '$.MOVIMIENTO_LOTE',\n" +
                "                 \"VALOR_0\"   Varchar2 ( 17) Path '$.VALOR_0',\n" +
                "                 \"Cierre\"   Varchar2 ( 17) Path '$.Cierre',\n" +
                "                 \"Periodo\"   Varchar2 ( 17) Path '$.Periodo',\n" +
                "                 \"NRO_ITEM\"   Varchar2 ( 17) Path '$.NRO_ITEM',\n" +
                "                 \"CENTRO_UTILIDAD\"   Varchar2 ( 17) Path '$.CENTRO_UTILIDAD',\n" +
                "                 \"SUBCENTRO_UTILIDAD\"   Varchar2 ( 17) Path '$.SUBCENTRO_UTILIDAD',\n" +
                "                 \"CUENTA_CONTABLE\"   Varchar2 ( 17) Path '$.CUENTA_CONTABLE',\n" +
                "                 \"CENTRO_COSTO\"   Varchar2 ( 17) Path '$.CENTRO_COSTO',\n" +
                "                 \"SUBCENTRO_COSTO\"   Varchar2 ( 17) Path '$.SUBCENTRO_COSTO',\n" +
                "                 \"TERCERO\"   Varchar2 ( 17) Path '$.TERCERO',\n" +
                "                 \"CONCEPTO_CUENTA\"   Varchar2 ( 1000) Path '$.CONCEPTO_CUENTA',\n" +
                "                 \"DOC_REF_1\"   Varchar2 ( 17) Path '$.DOC_REF_1',\n" +
                "                 \"DOC_REF_2\"   Varchar2 ( 17) Path '$.DOC_REF_2',\n" +
                "                 \"TIPO_MVTO\"   Varchar2 ( 17) Path '$.TIPO_MVTO',\n" +
                "                 \"VALOR_MVTO\"   Varchar2 ( 17) Path '$.VALOR_MVTO',\n" +
                "                 \"VALOR_BASE\"   Varchar2 ( 17) Path '$.VALOR_BASE',\n" +
                "                 \"VALOR_1\"   Varchar2 ( 17) Path '$.VALOR_1',\n" +
                "                 \"VALOR_2\"   Varchar2 ( 17) Path '$.VALOR_2',\n" +
                "                 \"VALOR_3\"   Varchar2 ( 17) Path '$.VALOR_3'\n" +
                "                          ) ) jt\n" +
                "  Where    --e.ID = 1249\n" +
                "  doc_ref in ("+listaAccounts+")\n" +
                "  and Documento = 'ZZ'\n" +
                "  order by ID desc";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                ObtenerRemanenteDesembolsoMasivoLegado obtenerCortoyLargoPlazoLegado= new ObtenerRemanenteDesembolsoMasivoLegado();;
                obtenerCortoyLargoPlazoLegado.setDocumentoIdentidad(rs.getString("DOC_REF"));
                obtenerCortoyLargoPlazoLegado.setDocumentoTransaccionId(rs.getString("DOCUMENTO"));
                obtenerCortoyLargoPlazoLegado.setConceptoCuenta(rs.getString("CONCEPTO_CUENTA"));
                obtenerCortoyLargoPlazoLegado.setNumeroCuenta(rs.getString("DOC_REF_2"));

                obtenerRCortoyLargoPlazoLegadosList.add(obtenerCortoyLargoPlazoLegado);
                System.out.println("Los resultados son" + obtenerCortoyLargoPlazoLegado.toString());

            }
        } catch (SQLException e) {
            System.out.println("no existen datos en la base de datos legado");
        }
        return obtenerRCortoyLargoPlazoLegadosList;

    }


    public List<ObtenerRemanenteDesembolsoMasivoLegado> consultarCausacionInteresesLegado(List<String> numAccount,String tipoInteres) {
        List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerInteresesdiariosLegadosList = new ArrayList<>();
        String listaAccounts="";
        for (String item:numAccount) {
            if (listaAccounts.equals("")){
                listaAccounts+="'"+item+"'";
            }else {
                listaAccounts+=",'"+item+"'";
            }

        }
        String query = "  Select jt.* --, e.*\n" +
                " From ucscd.estructura_ws e ,\n" +
                "      JSON_TABLE(respuesta , '$[*]'\n" +
                "                 Columns (\"doc_ref\"   Varchar2 ( 17) Path '$.DOC_REF_2',\n" +
                "                 \"Documento\"   Varchar2 ( 17) Path '$.DOCUMENTO',\n" +
                "                 \"NUMERO\"   Varchar2 ( 17) Path '$.NUMERO',\n" +
                "                 \"CODIGO_PAIS\"   Varchar2 ( 17) Path '$.CODIGO_PAIS',\n" +
                "                 \"NIT_EMPRESA\"   Varchar2 ( 17) Path '$.NIT_EMPRESA',\n" +
                "                 \"FECHA_MOVIMIENTO\"   Varchar2 ( 17) Path '$.FECHA_MOVIMIENTO',\n" +
                "                 \"DESCRIPCION_GENERAL\"   Varchar2 ( 1000) Path '$.DESCRIPCION_GENERAL',\n" +
                "                 \"MOVIMIENTO_LOTE\"   Varchar2 ( 17) Path '$.MOVIMIENTO_LOTE',\n" +
                "                 \"VALOR_0\"   Varchar2 ( 17) Path '$.VALOR_0',\n" +
                "                 \"Cierre\"   Varchar2 ( 17) Path '$.Cierre',\n" +
                "                 \"Periodo\"   Varchar2 ( 17) Path '$.Periodo',\n" +
                "                 \"NRO_ITEM\"   Varchar2 ( 17) Path '$.NRO_ITEM',\n" +
                "                 \"CENTRO_UTILIDAD\"   Varchar2 ( 17) Path '$.CENTRO_UTILIDAD',\n" +
                "                 \"SUBCENTRO_UTILIDAD\"   Varchar2 ( 17) Path '$.SUBCENTRO_UTILIDAD',\n" +
                "                 \"CUENTA_CONTABLE\"   Varchar2 ( 17) Path '$.CUENTA_CONTABLE',\n" +
                "                 \"CENTRO_COSTO\"   Varchar2 ( 17) Path '$.CENTRO_COSTO',\n" +
                "                 \"SUBCENTRO_COSTO\"   Varchar2 ( 17) Path '$.SUBCENTRO_COSTO',\n" +
                "                 \"TERCERO\"   Varchar2 ( 17) Path '$.TERCERO',\n" +
                "                 \"CONCEPTO_CUENTA\"   Varchar2 ( 1000) Path '$.CONCEPTO_CUENTA',\n" +
                "                 \"DOC_REF_1\"   Varchar2 ( 17) Path '$.DOC_REF_1',\n" +
                "                 \"DOC_REF_2\"   Varchar2 ( 17) Path '$.DOC_REF_2',\n" +
                "                 \"TIPO_MVTO\"   Varchar2 ( 17) Path '$.TIPO_MVTO',\n" +
                "                 \"VALOR_MVTO\"   Varchar2 ( 17) Path '$.VALOR_MVTO',\n" +
                "                 \"VALOR_BASE\"   Varchar2 ( 17) Path '$.VALOR_BASE',\n" +
                "                 \"VALOR_1\"   Varchar2 ( 17) Path '$.VALOR_1',\n" +
                "                 \"VALOR_2\"   Varchar2 ( 17) Path '$.VALOR_2',\n" +
                "                 \"VALOR_3\"   Varchar2 ( 17) Path '$.VALOR_3'\n" +
                "                          ) ) jt\n" +
                "  Where    --e.ID = 1249\n" +
                "  doc_ref in ("+listaAccounts+")\n" +
                "  and Documento = '"+tipoInteres+"'\n" +
                "  order by ID desc";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                ObtenerRemanenteDesembolsoMasivoLegado obtenerCortoyLargoPlazoLegado= new ObtenerRemanenteDesembolsoMasivoLegado();
                obtenerCortoyLargoPlazoLegado.setDocumentoIdentidad(rs.getString("DOC_REF"));
                obtenerCortoyLargoPlazoLegado.setDocumentoTransaccionId(rs.getString("DOCUMENTO"));
                obtenerCortoyLargoPlazoLegado.setConceptoCuenta(rs.getString("CONCEPTO_CUENTA"));
                obtenerCortoyLargoPlazoLegado.setNumeroCuenta(rs.getString("DOC_REF_2"));

                obtenerInteresesdiariosLegadosList.add(obtenerCortoyLargoPlazoLegado);
                System.out.println("Los resultados son" + obtenerCortoyLargoPlazoLegado.toString());

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" no existen datos en la base de datos legado");
        }
        return obtenerInteresesdiariosLegadosList;

    }



    public List<ObtenerRemanenteDesembolsoMasivoLegado>  consultarMovimientosEntradasContables(String numAccount) throws InterruptedException {
        List<ObtenerRemanenteDesembolsoMasivoLegado> obtenerEntradasContablesList = new ArrayList<>();
        String query = "  Select jt.* --, e.*\n" +
                " From ucscd.estructura_ws e ,\n" +
                "      JSON_TABLE(respuesta , '$[*]'\n" +
                "                 Columns (\"doc_ref\"   Varchar2 ( 17) Path '$.DOC_REF_2',\n" +
                "                 \"Documento\"   Varchar2 ( 17) Path '$.DOCUMENTO',\n" +
                "                 \"NUMERO\"   Varchar2 ( 17) Path '$.NUMERO',\n" +
                "                 \"CODIGO_PAIS\"   Varchar2 ( 17) Path '$.CODIGO_PAIS',\n" +
                "                 \"NIT_EMPRESA\"   Varchar2 ( 17) Path '$.NIT_EMPRESA',\n" +
                "                 \"FECHA_MOVIMIENTO\"   Varchar2 ( 17) Path '$.FECHA_MOVIMIENTO',\n" +
                "                 \"DESCRIPCION_GENERAL\"   Varchar2 ( 1000) Path '$.DESCRIPCION_GENERAL',\n" +
                "                 \"MOVIMIENTO_LOTE\"   Varchar2 ( 17) Path '$.MOVIMIENTO_LOTE',\n" +
                "                 \"VALOR_0\"   Varchar2 ( 17) Path '$.VALOR_0',\n" +
                "                 \"Cierre\"   Varchar2 ( 17) Path '$.Cierre',\n" +
                "                 \"Periodo\"   Varchar2 ( 17) Path '$.Periodo',\n" +
                "                 \"NRO_ITEM\"   Varchar2 ( 17) Path '$.NRO_ITEM',\n" +
                "                 \"CENTRO_UTILIDAD\"   Varchar2 ( 17) Path '$.CENTRO_UTILIDAD',\n" +
                "                 \"SUBCENTRO_UTILIDAD\"   Varchar2 ( 17) Path '$.SUBCENTRO_UTILIDAD',\n" +
                "                 \"CUENTA_CONTABLE\"   Varchar2 ( 17) Path '$.CUENTA_CONTABLE',\n" +
                "                 \"CENTRO_COSTO\"   Varchar2 ( 17) Path '$.CENTRO_COSTO',\n" +
                "                 \"SUBCENTRO_COSTO\"   Varchar2 ( 17) Path '$.SUBCENTRO_COSTO',\n" +
                "                 \"TERCERO\"   Varchar2 ( 17) Path '$.TERCERO',\n" +
                "                 \"CONCEPTO_CUENTA\"   Varchar2 ( 1000) Path '$.CONCEPTO_CUENTA',\n" +
                "                 \"DOC_REF_1\"   Varchar2 ( 17) Path '$.DOC_REF_1',\n" +
                "                 \"DOC_REF_2\"   Varchar2 ( 17) Path '$.DOC_REF_2',\n" +
                "                 \"TIPO_MVTO\"   Varchar2 ( 17) Path '$.TIPO_MVTO',\n" +
                "                 \"VALOR_MVTO\"   Varchar2 ( 17) Path '$.VALOR_MVTO',\n" +
                "                 \"VALOR_BASE\"   Varchar2 ( 17) Path '$.VALOR_BASE',\n" +
                "                 \"VALOR_1\"   Varchar2 ( 17) Path '$.VALOR_1',\n" +
                "                 \"VALOR_2\"   Varchar2 ( 17) Path '$.VALOR_2',\n" +
                "                 \"VALOR_3\"   Varchar2 ( 17) Path '$.VALOR_3'\n" +
                "                          ) ) jt\n" +
                "  Where    --e.ID = 1249\n" +
                "  doc_ref in ('"+numAccount+"')\n" +
                "  and Documento = '75'\n" +
                "  order by ID desc";
        System.out.println(query);
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                ObtenerRemanenteDesembolsoMasivoLegado obtenerentradasContables= new ObtenerRemanenteDesembolsoMasivoLegado();;
                obtenerentradasContables.setDocumentoIdentidad(rs.getString("DOC_REF"));
                obtenerentradasContables.setDocumentoTransaccionId(rs.getString("DOCUMENTO"));
                obtenerentradasContables.setConceptoCuenta(rs.getString("CONCEPTO_CUENTA"));
                obtenerentradasContables.setNumeroCuenta(rs.getString("DOC_REF_2"));
                obtenerentradasContables.setValorMonto(rs.getString("VALOR_MVTO"));

                obtenerEntradasContablesList.add(obtenerentradasContables);
                System.out.println("Los resultados son" + obtenerentradasContables.toString());

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" no existen datos en la base de datos legado");
        }
        return obtenerEntradasContablesList;


    }




}



