
package com.demo3.demo3.cucumberTests;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
 class CucumberIntegrationTest extends SpringIntegrationTest {
}

