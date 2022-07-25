package com.accesshq.TestSuite;
import com.accesshq.Models.MenuBar;
import com.accesshq.Models.PlanetCard;
import com.accesshq.Models.PlanetPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;

public class PlanetsTestSuite {
    Page page;
    MenuBar menu;
    Browser browser;
    @BeforeEach
    public void setup(){
    Playwright playwright = Playwright.create();
    browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(false).setSlowMo(50));
    BrowserContext context = browser.newContext(new Browser.NewContextOptions());
    page = context.newPage();
    page.navigate("https://d18u5zoaatmpxx.cloudfront.net/#/");
    menu = new MenuBar(page);
    }

    @Test
    public void testPlanetEarth() throws FileNotFoundException {
        //arrange
        menu.goToPage("planets");
        PlanetCard planet = new PlanetPage(page).getPlanet(p->p.getPlanetName()
                .equalsIgnoreCase("Earth"));

        //act
        planet.clickExploreButton();

        //assert
        Assertions.assertEquals("Exploring Earth", page
                .locator(".snackbar.popup-message.mr-auto").innerText());
    }

    @Test
    public void testFarthestPlanet() throws FileNotFoundException {
        //arrange
        menu.goToPage("planets");
        PlanetCard planet = new PlanetPage(page).getPlanet(p->p.getPlanetName()
                .equalsIgnoreCase("Neptune"));

        //act
        planet.clickExploreButton();

        //assert
        Assertions.assertEquals(planet.getPlanetDistance(),
                new PlanetPage(page).getFarthestDistance());
    }

    @AfterEach
    public void tearDown(){
     browser.close();
    }
}
