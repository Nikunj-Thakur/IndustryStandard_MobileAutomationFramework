package com.qa.utils;

public class GlobalParams {

    //ThreadLocal is a special type of variable in Java that provides thread-local storage.
    //Each Thread that accesses a ThreadLocal variable has its own independent copy of that variable
    //This is very useful in parallel execution as changes made by one thread do not impact the value of variable for other threads

    //Common for both Android and IOS
    private static ThreadLocal<String> platformName=new ThreadLocal<String>();
    private static ThreadLocal<String> udid=new ThreadLocal<String>();
    private static ThreadLocal<String> deviceName=new ThreadLocal<String>();

    //For Android
    private static ThreadLocal<String> systemPort=new ThreadLocal<String>();
    private static ThreadLocal<String> chromeDriverPort=new ThreadLocal<String>();

    //For IOS

    private static ThreadLocal<String> wdaLocalPort=new ThreadLocal<String>();
    private static ThreadLocal<String> webkitDebugProxyPort=new ThreadLocal<String>();

    public void setPlatformName(String platformName1){
        platformName.set(platformName1);
    }
    public String getPlatformName(){
        return platformName.get();
    }

    public void setUdid(String udid1){
        udid.set(udid1);
    }
    public String getUdid(){
        return udid.get();
    }

    public void setDeviceName(String deviceName1){
        deviceName.set(deviceName1);
    }
    public String getDeviceName(){
        return deviceName.get();
    }

    public void setSystemPort(String systemPort1){
        systemPort.set(systemPort1);
    }
    public String getSystemPort(){
        return systemPort.get();
    }

    public void setChromeDriverPort(String chromeDriverPort1){
        chromeDriverPort.set(chromeDriverPort1);
    }
    public String getChromeDriverPort(){
        return chromeDriverPort.get();
    }

    public void setWdaLocalPort(String wdaLocalPort1){
        wdaLocalPort.set(wdaLocalPort1);
    }
    public String getWdaLocalPort(){
        return wdaLocalPort.get();
    }

    public void setWebkitDebugProxyPort(String webkitDebugProxyPort1){
        webkitDebugProxyPort.set(webkitDebugProxyPort1);
    }
    public String getWebkitDebugProxyPort(){
        return webkitDebugProxyPort.get();
    }

    public void initializeGlobalParams(){
        GlobalParams globalParams=new GlobalParams();

        globalParams.setPlatformName(System.getProperty("platformName","Android"));
        globalParams.setUdid(System.getProperty("udid","2107f8f1"));
        globalParams.setDeviceName(System.getProperty("deviceName","OnePlus"));

        switch (globalParams.getPlatformName()){
            case "Android":
                globalParams.setSystemPort(System.getProperty("systemPort","10000"));
                globalParams.setChromeDriverPort(System.getProperty("chromeDriverPort","11000"));
                break;
            case "IOS":
                globalParams.setWdaLocalPort(System.getProperty("wdaLocalPort","10001"));
                globalParams.setWebkitDebugProxyPort(System.getProperty("webkitDebugProxyPort","11001"));
                break;
            default:
                throw new IllegalStateException("Invalid Platform Name!");
        }
    }
}
