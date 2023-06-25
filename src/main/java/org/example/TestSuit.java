package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;



public class TestSuit {
    protected static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/test/java/Drivers/chromedriver.exe");
        //chrome will open
        driver = new ChromeDriver(); //(in POM file)import chrome web-Driver dependency)
        //waiting of duration
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //duration to be wait             //ImplicityWait
        //screen maximize
        driver.manage().window().maximize(); //maximizing screen
        //type URL opening web page
        driver.get("https://sandpiperweb.azureedge.net/Login");
        //==================================================================================================================

        //Click on registration Buttion-------------------------------------

        //driver.findElement(By.className("ico-register")); //1
        clickOnElement(By.className("ico-register"));       //2

        //driver.findElement(By.xpath("//input[@id=\"gender-female\"]")).click(); //radio button
        clickOnElement(By.id("gender-female"));

        //Enter First Name------------------------
        //driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("Smita");
        //driver.findElement(By.id("FirstName")).sendKeys("Smita"); //1
        typeText(By.id("FirstName"), "Smita");            //2

        //Enter Last Name-------------------------
        //driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Patel");
        //driver.findElement(By.id("LastName")).sendKeys("Patel");    //1
        typeText(By.id("LastName"), "Patel");              //2

        //Date of birth day----------------------
        Select birthDay = new Select(driver.findElement(By.name("DateOfBirthDay")));//select on the day
        birthDay.selectByIndex(28);

        //Date of birth month---------------------
        Select birtMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));//select on month
        birtMonth.selectByIndex(6);

        //Date of birth year--------------------
        Select birthYear = new Select(driver.findElement(By.name("DateOfBirthYear")));//select on year
        birthYear.selectByVisibleText("1990");

        //Enter Email----------------------------
        System.out.println(randomDate());
        //driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("smita.mpatel164@gmail.com");
        //driver.findElement(By.id("Email")).sendKeys("smita.mpatel164@gmail.com");
        typeText(By.id("Email"), "smita.mpatel164" + randomDate() + "@gmail.com");

        //Enter Email----------------------------
        //driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("mpatel164");
        //driver.findElement(By.id("Password")).sendKeys("mpatel164");
         typeText(By.id("Password"), "mpatel164");

        //Enter your confirm password-------------
        //driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("mpatel164");
        //driver.findElement(By.id("ConfirmPassword")).sendKeys("mpatel164");
        typeText(By.id("ConfirmPassword"), "mpatel164");
        //-------------------------------------------------------------------------------------------------------------------------------------

        //Click on register button------------------------
        //driver.findElement(By.xpath("//button[@id='register-button']")).click(); //belowsecond option  clickOnElement(By.id("register-button"));
        clickOnElement(By.id("register-button"));

        //DriverWaait calling    // Explicity Wait any were u can use
        driverWaitsUntilURLToBe(20, "https://sandpiperweb.azureedge.net/Registration");//call the methode and put the code any where you can use
        driverWaitsUntilURLToBe(50, "https://sandpiperweb.azureedge.net"); //driver wait then go next

        String expectedMessage = "Your registration completed";

        String actualMessage = getTextFromElement(By.className("result")); //getteext retun method use here
        System.out.println("Actual Message:" + actualMessage);

        if (expectedMessage.equals(actualMessage)) {
            System.out.println("Your registration completed");
        } else {
            System.out.println("Your registration is not completed");
        }


        //Click on registration Buttion
        clickOnElement(By.className("ico-login"));

        //Enter Email-------------------------------
        typeText(By.className("email"), "asmisha06@gmail.com"); //method 4

        //Enter Password-------------------------------
        typeText(By.id("Password"), "asmisha06");

        //Click on login button------------------
        clickOnElement(By.className("button-1 login-button"));
    }


//========================================================================================================================
    //All reuseble methods
    //1 method-----------------------------------
    public static void clickOnElement(By by){                    //driver.findElement(By.xpath("//button[@id='register-button']")).click();
        driver.findElement(by).click();
    }
    //2Method-----------------------------------
    public static void getURL(int time, String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    //4 method----------------------------------
    public static String randomDate() {  //reuseble methode ...one time creat and all
        Date date = new Date();  //2
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
        return formatter.format(date);

        //Date date = new Date();  //1
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/HH/mm/ss");
        //String strDate = formatter.format(date);
        //System.out.println(strDate);
    }
    //5 method----------------------------------
    public static String getTextFromElement(By by) {
        //driver.findElement(by).getText();
        return driver.findElement(by).getText();
    }
    //6 method----------------------------------
    public static String timeStamp() {
        SimpleDateFormat date = new SimpleDateFormat("yyMMddHHmmss");
        return timeStamp();
    }
    //7 method----------------------------------
    public static void click(By by) {
        driver.findElement(by).click();
    }
    //8 method----------------------------------
    public static void sendKeys(By by, String text) { //enter firstname
        driver.findElement(by).sendKeys(text);
    }
    //10 method----------------------------------

    public static void typeText(By by, String text)
    {
        driver.findElement(by).sendKeys(text);
    }
    //11 method----------------------------------


    public void selectByValueDropDown(By by, String value) {
        Select select = new Select(driver.findElement(by));
        select.selectByValue(value);
    }
//    public static void verifyCurrentUrl(String url){
//        Assert.assertEquals(driver.getCurrentUrl(),url);
//    }
//=====================================================================================================================
    //13 method----------------------------------
    public static void driverWaitsUntilURLToBe(int time, String url) {
        WebDriverWait wait01 = new WebDriverWait(driver, Duration.ofSeconds(time));
        //wait.until(ExpectedCondition.elementToBeClickable(By.Xpath("//button[@name='register-button']")));
        wait01.until(ExpectedConditions.urlToBe(url));                             //in same class you cant use same object name

//
        //========================================================================================================
    }


    public static void driverWaitsUntilURLTobeClickable(By by, int time) { //1
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }                                                                                             //1driverWait commands

    public static void driverWait(int time, String urlFraction) { //2
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlContains(urlFraction));
    }                                                                                             //3driverWait commands

    public static void driveWait(int time, String Attribute, String value, By by) { //3
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.attributeContains(by, Attribute, value));
    }                                                                                           //4driverWait commands

    public static void driverWaitUrlContains(int time, By by, String urlName) { //4
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlContains(urlName));
    }                                                                                          //5driverWait commands

    public static void DriverWaitSelectElement(int time, By by) { //5
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
    }                                                                                           //6driverWait commands

    public static void DriverWaitPresenceOfAllElementsLocatedBy(int time, By by) { //6
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }                                                                                            //7driverWait commands

    public static void DriverWaitVisibilityOf(int time, WebElement element) { //7
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
    }                                                                                             //8driverWait commands

    public static void DriverWaituntilinvisibilityOfWebElement(int time, WebElement element) { //8
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }                                                                                             //9driverWait commands

    public static void DriverWaitElementToBeSelected(int time, By by) { //9
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeSelected(by));
    }                                                                                            //10driverWait commands

    public static void DriverWait(int time, String name) { //10
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.titleIs(name));
    }

//============================================================================================================
//Sleep method
    public static void sleep(int time){
        try {
            Thread.sleep(1000 * time);
        }catch (InterruptedException e){
            e.printStackTrace();
                             //calling   " sleep(time 3000); "     you can use this comand and put there
        }
    }















//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//Screenshot code-----------
//<<<<<<<<<<<<<<<

//
//  //  public static void takeSnapShot(String a) {
//        TakesScreenshot scrShot = ((TakesScreenshot) driver);
//        //Call getScreenShots method to create image file
//        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
//
//        // copy file at destination
//
//        try {
//            FileUtils.copyFile(srcFile, new File("Screenshots\\" + a + randomDate() + ".png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//====================================================================================

//=====================================================================================
//    public static void main(String args[]){
//        addNumber(5,3);
//        addNumber(8,3);
//        addNumber(4,3);
//
//    public static void addNumber(int a, int b){
//            System.out.println(a+b);
//        }
//    }
//=========================================================================================================
}