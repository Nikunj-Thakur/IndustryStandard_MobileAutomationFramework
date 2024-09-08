package com.qa.runners.Squad_2;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.ServerManager;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.ThreadContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {
                "pretty",
                "html:target/cucumber/cucumber.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.qa.stepdefs.StepDetails",
                "summary"}
        ,features = {"src/test/resources/features/Squad_2"}
        ,glue = {"com.qa.stepdefs"}
        ,snippets = CAMELCASE
        ,dryRun = false
        ,monochrome = true,
         tags = "@P1 or @P2"


)

public class Squad_2_RunnerTest {

    @BeforeClass
    public static void initialize() throws Exception {
        GlobalParams globalParams=new GlobalParams();
        globalParams.initializeGlobalParams();
        ThreadContext.put("ROUTINGKEY",globalParams.getPlatformName() + "_" + globalParams.getDeviceName());
        new ServerManager().startServer();
        new DriverManager().initializeDriver();
    }

    @AfterClass
    public static void tearDown(){
        DriverManager driverManager = new DriverManager();
        if (driverManager.getDriver() != null) {
            driverManager.getDriver().quit();
            driverManager.setDriver(null);
        }

        ServerManager serverManager=new ServerManager();
        if(serverManager.getServer()!=null){
            serverManager.getServer().stop();
        }
    }
}
