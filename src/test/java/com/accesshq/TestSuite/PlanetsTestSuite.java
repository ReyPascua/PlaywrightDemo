package com.accesshq.TestSuite;
import com.accesshq.Models.MenuPage;
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
    MenuPage menu = new MenuPage(page);
    menu.selectMenu("planets");
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

    @Test
    public void testFarthestPlanet(){
        //arrange
        PlanetPage planetPage = new PlanetPage(page);

        //act
        planetPage.clickNeptune();

        //assert
        Assertions.assertTrue(page.locator("text=Exploring Neptune").isVisible());
        Assertions.assertEquals("4495000",String.valueOf(planetPage.getFarthestDistance()));
    }


    @AfterEach
    public void tearDown(){
     browser.close();
    }
}
