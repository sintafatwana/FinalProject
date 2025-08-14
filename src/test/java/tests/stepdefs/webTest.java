package tests.stepdefs;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"tests.stepdefs", "hooks"},
        features = {"src/test/resources"},
        tags = "@web",
        plugin = {"pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:reports/web-report.html"}
)

public class webTest {
}
