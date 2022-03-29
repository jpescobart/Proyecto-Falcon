package co.avista.utils;

import co.avista.logs.Log;
import cucumber.api.Scenario;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ExecutionInformation {
    private static String scenario="";
    private static String feature="";
    private static String evidencePath="";
    private static boolean firstExecution=true;

    private ExecutionInformation() {
        throw new IllegalStateException("Utility class");
    }

    public static void setExecutionInformation(Scenario currentScenario) throws IOException {
        scenario=currentScenario.getName();
        String rawFeatureName = currentScenario.getId().split(";")[0].replace("-"," ");
        feature=rawFeatureName.substring(0, 1).toUpperCase() + rawFeatureName.substring(1);

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/properties/configuration.properties"));
        evidencePath=prop.getProperty("EVIDENCEPATH");
        Log.startLogger(evidencePath, feature, scenario, firstExecution);
        firstExecution=false;
    }


    public static String getScenario() {
        return scenario;
    }

    public static void setScenario(String scenario) {
        ExecutionInformation.scenario = scenario;
    }

    public static String getFeature() {
        return feature;
    }

    public static void setFeature(String feature) {
        ExecutionInformation.feature = feature;
    }

    public static String getEvidencePath() {
        return evidencePath;
    }

    public static void setEvidencePath(String evidencePath) {
        ExecutionInformation.evidencePath = evidencePath;
    }
}
