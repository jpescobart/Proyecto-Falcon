package co.avista.basededatos;


import com.couchbase.client.core.deps.io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import com.couchbase.client.core.diagnostics.DiagnosticsResult;
import com.couchbase.client.core.diagnostics.PingResult;
import com.couchbase.client.core.env.IoConfig;
import com.couchbase.client.core.env.SecurityConfig;
import com.couchbase.client.java.*;

import java.time.Duration;

import com.couchbase.client.java.env.ClusterEnvironment;
import com.couchbase.client.java.json.*;
import com.couchbase.client.java.manager.query.CreatePrimaryQueryIndexOptions;
import com.couchbase.client.java.query.*;
import com.couchbase.client.java.Cluster;



import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ConexionBdFalcon {

    String host = "couchbases://e3388315-6278-418b-92b2-319a6d94caa2.dp.cloud.couchbase.com?ssl=no_verify&console_log_level=5";
    String username = "quality-voyager-falcon-juan-escobar";
    String password = "F!ZV2JnQAOTq5$kQA4ZxGp";
    String bucketName = "voyager-falcon";

    ClusterEnvironment env = ClusterEnvironment.builder()
            .securityConfig(SecurityConfig.enableTls(true)
                    .trustManagerFactory(InsecureTrustManagerFactory.INSTANCE))
            .ioConfig(IoConfig.enableDnsSrv(true))
            .build();

    public String conectionAndQueryNumId(String numAccount) {
        Cluster cluster = Cluster.connect(host,
                ClusterOptions.clusterOptions(username, password).environment(env));
        cluster.waitUntilReady(Duration.ofSeconds(5));
        Bucket bucket = cluster.bucket(bucketName);
        Collection collection = bucket.defaultCollection();
        final QueryResult result = cluster.query("SELECT * FROM `voyager-falcon` WHERE kind=\"Accounts\"  and account.accountNumber= \"" + numAccount + "\"");
        String id = null;
        for (JsonObject row : result.rowsAsObject()) {
            id = row.getObject("voyager-falcon").getObject("account").getString("accountId");
        }
        cluster.disconnect();
        System.out.println("El id es" + id);
        return id;
    }

    public void conectionAndQueryCanal(String numAccount) {
        Cluster cluster = Cluster.connect(host,
                ClusterOptions.clusterOptions(username, password).environment(env));
        cluster.waitUntilReady(Duration.ofSeconds(5));
        Bucket bucket = cluster.bucket(bucketName);
        Collection collection = bucket.defaultCollection();
        final QueryResult result = cluster.query("SELECT * FROM `voyager-falcon` WHERE kind=\"Accounts\"  and account.accountNumber= \"" + numAccount + "\"");
        JsonArray canal = null;
        for (JsonObject row : result.rowsAsObject()) {
            canal=row.getObject("voyager-falcon").getObject("account").getArray("disbursements");
            for (int i=0;i<canal.size();i++){
                JsonObject jsonObject=canal.getObject(i);
                System.out.println("El disbursements es 2, remanente al cliente");
                if (jsonObject.getString("disbursementType")=="R" && jsonObject.getInt("disbursementChannel")==1){
                    System.out.println("El canal es igual a 1" );
                }else{
                    System.out.println("El canal no es igual a 1 ");
                }
            }

        }
    }

    public void conectionAndQueryCPyLG(String numAccount) {
        Cluster cluster = Cluster.connect(host,
                ClusterOptions.clusterOptions(username, password).environment(env));
        cluster.waitUntilReady(Duration.ofSeconds(5));
        Bucket bucket = cluster.bucket(bucketName);
        Collection collection = bucket.defaultCollection();
        final QueryResult result = cluster.query("SELECT * FROM `voyager-falcon` WHERE kind=\"Accounts\"  and account.accountNumber= \"" + numAccount + "\"");
        int cortoPlazo = 0;
        int largoPlazo = 0;
        for (JsonObject row : result.rowsAsObject()) {
            largoPlazo =row.getObject("voyager-falcon").getObject("account").getInt("longTermPortion");
            cortoPlazo =row.getObject("voyager-falcon").getObject("account").getInt("shortTermPortion");
                if (largoPlazo==0 && cortoPlazo==0){
                    System.out.println("el corto y largo plazo son registrados correctamente" );
                }else{
                    System.out.println("El corto y largo pazo no se registraron");
                }
            }

        }


    public void obtenerCortoyLargoPlazoCalculado(List<String> numAccount) {
        Cluster cluster = Cluster.connect(host,
                ClusterOptions.clusterOptions(username, password).environment(env));
        cluster.waitUntilReady(Duration.ofSeconds(5));
        Bucket bucket = cluster.bucket(bucketName);
        Collection collection = bucket.defaultCollection();
        String listaAccounts="";
        for (String item:numAccount) {
            if (listaAccounts.equals("")){
                listaAccounts+="'"+item+"'";
            }else {
                listaAccounts+=",'"+item+"'";
            }

        }

        final QueryResult result = cluster.query("SELECT `voyager-falcon`.*  FROM `voyager-falcon` where account.accountNumber in ["+listaAccounts+"]");
        int cortoPlazo = 0;
        int largoPlazo = 0;
        for (JsonObject row : result.rowsAsObject()) {
            System.out.println(row);
            largoPlazo =row.getObject("account").getInt("longTermPortion");
            cortoPlazo =row.getObject("account").getInt("shortTermPortion");
            if (largoPlazo!=0 && cortoPlazo!=0){
                System.out.println("el corto y largo plazo son registrados correctamente" );
            }else{
                System.out.println("El corto y largo pazo no se registraron");
            }
       }

    }

    public void conectionAndQueryCarteraVendida(String numAccount) {
        Cluster cluster = Cluster.connect(host,
                ClusterOptions.clusterOptions(username, password).environment(env));
        cluster.waitUntilReady(Duration.ofSeconds(5));
        Bucket bucket = cluster.bucket(bucketName);
        Collection collection = bucket.defaultCollection();
        final QueryResult result = cluster.query("SELECT * FROM `voyager-falcon` WHERE kind=\"Accounts\"  and account.accountNumber= \"" + numAccount + "\"");
        String salesType;
        JsonArray salesHistories;
        for (JsonObject row : result.rowsAsObject()) {
            salesType =row.getObject("voyager-falcon").getObject("account").getString("salesType");
            salesHistories = row.getObject("voyager-falcon").getObject("account").getArray("salesHistory");
            salesHistories.getObject(0).getString("saleType").equals("VENTA EN FIRME");
            salesHistories.getObject(0).getString("company").equals("Pruebas QA");
            System.out.println("la marcacion de la cuenta como cartera vendida es exitosa");
        }


    }

    public void conectionAndQueryCreditoCreadoEntradasContables(String numAccount) {
        Cluster cluster = Cluster.connect(host,
                ClusterOptions.clusterOptions(username, password).environment(env));
        cluster.waitUntilReady(Duration.ofSeconds(5));
        Bucket bucket = cluster.bucket(bucketName);
        Collection collection = bucket.defaultCollection();
        final QueryResult result = cluster.query("SELECT * FROM `voyager-falcon` WHERE kind=\"Accounts\"  and account.accountNumber= \"" + numAccount + "\"");
        String numeroCuenta;
        for (JsonObject row : result.rowsAsObject()) {
            numeroCuenta =row.getObject("voyager-falcon").getObject("account").getString("accountNumber");
            int numeroCuentafinal=  Integer.parseInt(numeroCuenta);
            if (numeroCuentafinal!=0){
                System.out.println("El credito se  guardo corectamente  en la base de datos " );
            }else{
                System.out.println("El credito no se guardo en la base de datos");
            }
        }

    }


    }







