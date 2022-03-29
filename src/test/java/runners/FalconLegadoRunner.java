package runners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features/"
        , glue = {"co/avista/steps/"}
        , plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumberProyectoBase.json","com.cucumber.listener.ExtentCucumberFormatter:"}
        , tags = {"@FALCONLEGADO"}
)

public class FalconLegadoRunner {

    @BeforeClass
    public static void setup() {
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter("");
        Path userDir = Paths.get(System.getProperty("user.dir"));
        Path resources = userDir.resolve("src").resolve("test").resolve("resources");
        Path extentConfig = resources.resolve("extent-config.xml");
        Path extentProperties = resources.resolve("extent.properties");
        extentHtmlReporter.loadXMLConfig(extentConfig.toString());
        extentHtmlReporter.loadConfig(extentProperties.toString());
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(extentHtmlReporter);
    }
}
