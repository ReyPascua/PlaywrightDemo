package com.accesshq.TestSuite;
import com.accesshq.Models.PlanetPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class PlanetsTestSuite {
    Page page;
    Browser browser;
    @BeforeEach
    public void setup(){
    Playwright playwright = Playwright.create();
    browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(false).setSlowMo(50));
    BrowserContext context = browser.newContext(new Browser.NewContextOptions().
            setViewportSize(1300, 600));
    page = context.newPage();
    page.navigate("https://d18u5zoaatmpxx.cloudfront.net/#/");
    page.locator("text=personHomeFormsPlanets >> [aria-label=\"planets\"]").click();
    }

    @Test
    public void TestPlanetEarth(){
        //arrange
        PlanetPage planetPage = new PlanetPage(page);

        //act
        planetPage.clickEarth();

        //assert
            Assertions.assertTrue(page.locator("text=Exploring Earth").isVisible());
    }

    @AfterEach
    public void tearDown(){
     browser.close();
    }
}
