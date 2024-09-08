package com.qa.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import java.io.File;
import java.util.HashMap;

public class ServerManager {

    //We are creating a new appium server instance for each of the mobile device, it's easier for debugging this way as logs for each mobile device will be
    //available in separate files

    private static ThreadLocal<AppiumDriverLocalService> server=new ThreadLocal<>();

    TestUtils utils=new TestUtils();  //we will need this object for logging purpose

    public AppiumDriverLocalService getServer(){
        return server.get();
    }

    public void startServer(){
        String os=System.getProperty("os.name");
        AppiumDriverLocalService server;
        if(os.toLowerCase().contains("win")){
           server= windowsGetAppiumService();
        }else{
            server= macGetAppiumService();
        }
        utils.log().info("Starting Appium Server on " + os + "...");

      server.start();
      if(server==null || !server.isRunning()){
          utils.log().fatal("Appium Server not started successfully. ABORT !!!");
          throw new AppiumServerHasNotBeenStartedLocallyException("Appium Server not started successfully. ABORT !!!");
      }
      server.clearOutPutStreams(); // Do not show appium server logs in the console

        this.server.set(server); // Assign local server object to class level thread local object
        utils.log().info("Appium Server Started");
    }

    public AppiumDriverLocalService getAppiumServerDefault(){
        return AppiumDriverLocalService.buildDefaultService(); //this default service works only on Windows, does not work on MAC
    }

    // Start Appium Server locally on Windows using customer buildService
    public AppiumDriverLocalService windowsGetAppiumService() {
        GlobalParams params = new GlobalParams();
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingAnyFreePort()   // avoids port conflicts
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withLogFile(new File(params.getPlatformName() + "_"
                        + params.getDeviceName() + File.separator + "Server.log")));  // We are storing appium server logs in this file
    }

    // Start Appium Server locally on MAC using custom buildService
    public AppiumDriverLocalService macGetAppiumService() {
        GlobalParams params = new GlobalParams();
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("PATH", "enter_your_path_here" + System.getenv("PATH"));
        environment.put("ANDROID_HOME", "enter_your_android_home_path");
        environment.put("JAVA_HOME", "enter_your_java_home_path");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingAnyFreePort()    // avoids port conflicts
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withEnvironment(environment)
                .withLogFile(new File(params.getPlatformName() + "_"
                        + params.getDeviceName() + File.separator + "Server.log")));  // We are storing appium server logs in this file
    }
}