package com.qa.stepdefs;

import com.qa.utils.DriverManager;
import com.qa.utils.VideoManager;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import java.io.IOException;

public class Hooks {

    @Before
    public void initializeDriver() {
        new VideoManager().startRecording();
    }

    @After
    public void quitDriver(Scenario scenario) throws IOException {
        if(scenario.isFailed()){
            byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png",scenario.getName());
        }
        new VideoManager().stopRecording(scenario.getName());
    }
}
