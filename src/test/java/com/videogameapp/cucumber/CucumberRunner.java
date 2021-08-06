package com.videogameapp.cucumber;

import com.videogameapp.TestBase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/features/videoGame.feature")
public class CucumberRunner extends TestBase {
}
