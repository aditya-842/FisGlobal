package stepdefinations;
import io.cucumber.java.an.E;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.security.PublicKey;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MyStepdefs {
  private WebDriverWait wait;
  
  WebDriver driver;
  
  @When("I open the firefox Browser")
    public void ChromeDriver(){
      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();
      driver.manage().window().maximize();
    }
    
    @Then("I navigate to {string}")
    public void i_navigate_to(String url) {
      driver.get(url);
    }

  @When("I search for {string}")
  public void i_search_for(String searchItem) {
    WebElement searchBox = driver.findElement(By.id("gh-ac"));
    searchBox.sendKeys(searchItem);
    searchBox.submit();
  }


  @When("I click on the first book in the list")
  public void i_click_on_the_first_book_in_the_list() throws InterruptedException {
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".s-item__link")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstResult);
    
    wait.until(ExpectedConditions.elementToBeClickable(firstResult));
    
    firstResult.click();

  }

  @When("I add the item to the cart")
  public void i_add_the_item_to_the_cart() {
    WebElement addToCartButton = driver.findElement(By.id("isCartBtn_btn"));
    addToCartButton.click();
  }

  @Then("the cart should display the updated item count")
  public void the_cart_should_display_the_updated_item_count() {
    WebElement cartCount = driver.findElement(By.id("gh-cart-n"));
    assertNotNull(cartCount);
    assertTrue(Integer.parseInt(cartCount.getText()) > 0);
    driver.quit();
  }
    
    
}
