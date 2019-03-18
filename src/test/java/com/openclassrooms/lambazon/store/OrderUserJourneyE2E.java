package com.openclassrooms.lambazon.store;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderUserJourneyE2E {

    // FIXME: Move to a Page model
    private static final String PRODUCT_LIST_ID_SELECTOR = "products";
    public static final String ORDER_PAGE = "%s/order";

    @LocalServerPort
    private int port;
    private WebDriver driver = null;
    private String base;

    private static final String LANDING_PAGE = "%s/";

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        this.base = "http://localhost:" + port;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void givenANewUser_whenAnEmptyCartIsCheckedOut_thenItShouldRequireContent() throws InterruptedException {
        //FIXME: Add page models as these tests are brittle

        // 1. Navigate to the store and interact with products
        driver.get(String.format(LANDING_PAGE, base));
        WebElement element = driver.findElement(By.id(PRODUCT_LIST_ID_SELECTOR));

        // 2. Navigate to the order page
        driver.get(String.format(ORDER_PAGE, base));

        // 3. now submit the order
        driver.findElement(By.id("order-button")).click();

        // 4. Wait for errors
        //driver.; // FIXME: Set an implicit wait on the driver
        WebElement submitButton = (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("errors")));


        //FIXME: To be i18n friendly this should be based on a selector not the text on the page
        // 4. Check that empty cart checks have not regressed
        assertThat(driver.getPageSource(), containsString("your cart is empty!"));
    }

}
