package com.webautomation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.github.javafaker.Faker;


import java.time.Duration;

public class DemoWebShop
{
    WebDriver driver = new ChromeDriver();

    public void launchBrowser() {
        // Automatically manage ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        // Maximize the window, delete cookies, and set timeouts
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }//End of launching the Browser method

    public  void openDemoWebShop() {
    //Opening application
    driver.get("https://demowebshop.tricentis.com/");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(By.className("ico-register")));
    registerLink.click();
}//end of the Open Demo Web Shop website
    public void registerUser() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Locate the radio button by its name attribute and click it
        WebElement genderRadioButton = driver.findElement(By.name("Gender"));
        genderRadioButton.click();

        // Create a Faker instance to generate random email
        Faker faker = new Faker();
        // Generate random FirstName, LastName, and Email using Faker
        String firstName = faker.name().firstName();  // Random First Name
        String lastName = faker.name().lastName();    // Random Last Name
        String randomEmail = faker.internet().emailAddress(); // Generate random email

        // Adding a 2-second delay before filling each field to avoid rapid input issues.
        Thread.sleep(2000);
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        Thread.sleep(2000);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        Thread.sleep(2000);
        driver.findElement(By.id("Email")).sendKeys(randomEmail);
        Thread.sleep(2000);
        driver.findElement(By.name("Password")).sendKeys("Loutie2024!");
        Thread.sleep(2000);
        driver.findElement(By.name("ConfirmPassword")).sendKeys("Loutie2024!");

        // Clicking on the 'register' button after filling in the mandatory fields.
        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable
                (By.id("register-button")));
        registerButton.click();

        // Clicking on the continue button after registration
        Thread.sleep(2000);
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//input[@type='button' and @class='button-1 register-continue-button'" +
                        "and @value='Continue']\n")));
        continueButton.click();

        /* Optional: Add a validation to check if the radio button was selected
        if (radioButton.isSelected()) {
            System.out.println("Radio button is selected.");
        } else {
            System.out.println("Radio button is not selected.");
        }*/

        // Logout after registration to ensure user is logged out before login test
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("ico-logout")));
        logoutButton.click();
        System.out.println("User successfully logged out.");
    }

    public void loginUser() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        // Click login button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("ico-login")));
        loginButton.click();
        // Fill in login details
        driver.findElement(By.id("Email")).sendKeys("bzana@mailinator.com");
        Thread.sleep(2000);
        driver.findElement(By.name("Password")).sendKeys("Loutie2024!");

        Thread.sleep(3000);
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@class='button-1 login-button' and @type='submit' and @value='Log in']")));
        signInButton.click();
    }


    public void computersTab() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        // Locate the "Computers" tab
        WebElement computersTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/computers' and normalize-space(text())='Computers']")));
        // Hover over the "Computers" tab to reveal the dropdown (if necessary)
        Actions actions = new Actions(driver);
        actions.moveToElement(computersTab).perform();

        // Wait until the "Desktops" option is visible and clickable
        WebElement desktopOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/desktops' and normalize-space(text())='Desktops']")));
        // Click the "Desktops" option
        desktopOption.click();
        Thread.sleep(5000);
    }

    public void addToCart() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for the "Add to Cart" button and click it
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@type='button' and @value='Add to cart' and contains(@class," +
                        "'product-box-add-to-cart-button')]\n")));
        addToCartButton.click();
        Thread.sleep(5000);
        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-button-72")));
        cartIcon.click();
    }

    public void viewAndClearCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        // Click login button
        Thread.sleep(3000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("bar-notification")));
        WebElement shoppingCart = driver.findElement(By.className("ico-cart"));
        shoppingCart.click();
        // Wait for the "Remove from Cart" button to be clickable and click it
        WebElement removeFromCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("removefromcart")));
        removeFromCartButton.click();

// Wait for the "Update Cart" button to be clickable and click it
        WebElement updateCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("updatecart")));
        updateCartButton.click();
    }

    public void logoutUser() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        // Locate the logout button and ensure it is clickable
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("ico-logout")));
        // Pause for 5 seconds before performing the logout action
        Thread.sleep(5000);
        // Click the logout button to log out the user
        logoutButton.click();
        System.out.println("User successfully logged out.");
    }//end of the logoutUser method

    public void closeBrowser(){
        //Keeping window tab open for a few seconds before closing it.
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8000));
        //Closing the window tab after the sleep time has elapsed.
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
    }

    public static void main( String[] args )throws InterruptedException
    {
        DemoWebShop demo = new DemoWebShop();
        demo.launchBrowser();
        demo.openDemoWebShop();
        demo.registerUser();
        demo.loginUser();
        demo.computersTab();
        demo.addToCart();
        demo.viewAndClearCart();
        demo.logoutUser();
        demo.closeBrowser();
     }
    }





