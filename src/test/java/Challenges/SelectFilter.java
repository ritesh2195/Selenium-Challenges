package Challenges;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class SelectFilter {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.t-mobile.com/tablets?INTNAV=tNav:Devices:Tablets");

        Thread.sleep(5000);

        //selectFilter("Brands","Alcatel","TCL","Samsung");

        //selectFilter("Brands","TCL");

        selectFilter("Deals","New","Special offer");

        //selectFilter("Operating System","all");

        //selectFilter("Operating System","Android","iPadOS");

        driver.close();
    }

    public static void selectFilter(String ...options) throws InterruptedException {

        JavascriptExecutor driver1 = (JavascriptExecutor) driver;

        List<WebElement> filters = driver.findElements(By.xpath("//legend"));

        for (WebElement filter:filters){

            if (filter.getText().equalsIgnoreCase(options[0])){

                //driver1.executeScript("arguments[0].click()",filter);

                filter.click();

                if (options[1].equalsIgnoreCase("all")){

                    List<WebElement> filterOptions = driver.findElements(By.xpath("//span[@class='filter-display-name']"));

                    for (WebElement filterOption:filterOptions){

                        try {

                            filterOption.click();

                        }catch (ElementClickInterceptedException e){

                            driver1.executeScript("arguments[0].click()",filterOption);
                        }
                    }
                }

                else {

                    for (int i=1;i<options.length;i++){

                        String text = options[i];

                        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'"+text+"')]//ancestor::mat-checkbox//input"));

                        try {

                            element.click();

                        }catch (ElementClickInterceptedException e){

                            driver1.executeScript("arguments[0].click()",element);
                        }

                    }
                }
            }
        }
    }
}
