package com.accesshq.TestSuite;

import com.accesshq.Models.FormPage;
import com.accesshq.Models.MenuPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.awt.*;

public class FormsTestSuite {
    Page page;
    @BeforeEach
    public void startup() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().
                launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        page = context.newPage();
    }

    @Test
    public void modernFormSuccessTest(){
        //Arrange
        MenuPage menu = new MenuPage(page);
        FormPage form = new FormPage(page);
        menu.selectMenu("forms");
        form.enterName("Test User");
        form.enterEmail("testuser@accesshq.com");
        form.clickState("NSW");
        form.clickAgree();

        //Act
        form.clickSubmit();

        //Assert
        page.waitForSelector("[aria-live=polite]");
        Assertions.assertTrue(page.locator("text=Thanks for your feedback Test User").isVisible());
    }
    @Test
    public void modernFormFailureTest(){
        //Arrange
        MenuPage menu = new MenuPage(page);
        FormPage form = new FormPage(page);
        menu.selectMenu("forms");

        //Act
        form.clickSubmit();

        //Assert
        Assertions.assertTrue(page.locator("text=Your name is required").isVisible());
        Assertions.assertTrue(page.locator("text=Your email is required").isVisible());
        Assertions.assertTrue(page.locator("text=You must agree to continue").isVisible());
    }
}
