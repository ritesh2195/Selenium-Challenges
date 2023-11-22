package Challenges;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class SelectFilter {

    public static void main(String[] args) throws InterruptedException {

        //selectFilter("Brands", "Alcatel", "Apple");

        //selectFilter("Brands","Alcatel","Apple","Samsung");

        selectFilter("Operating System","all");
    }

    public static void selectFilter(String ...options) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.t-mobile.com/tablets?INTNAV=tNav:Devices:Tablets");

        Thread.sleep(5000);

        JavascriptExecutor driver1 = (JavascriptExecutor) driver;

        List<WebElement> filters = driver.findElements(By.xpath("//legend"));

        for (WebElement filter:filters){

            if (filter.getText().equalsIgnoreCase(options[0])){

                driver1.executeScript("arguments[0].click()",filter);

                if (options[1].equalsIgnoreCase("all")){

                    List<WebElement> filterOptions = driver.findElements(By.xpath("//span[@class='filter-display-name']"));

                    for (WebElement filterOption:filterOptions){

                        driver1.executeScript("arguments[0].click()",filterOption);
                    }
                }

                else {

                    for (int i=1;i<options.length;i++){

                        String text = options[i];

                        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'"+text+"')]//ancestor::mat-checkbox//input"));

                        driver1.executeScript("arguments[0].click()",element);
                    }
                }
            }
        }
    }
}
