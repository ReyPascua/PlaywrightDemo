package com.accesshq.TestSuite;

import com.accesshq.Models.FormPage;
import com.accesshq.Models.MenuBar;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class FormsTestSuite {
    Page page;
    MenuBar menu;
    Browser browser;
    @BeforeEach
    public void startup() {
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().
                launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        BrowserContext context = browser.newContext();
        page = context.newPage();
        menu = new MenuBar(page);
        page.navigate("https://d18u5zoaatmpxx.cloudfront.net/#/");
    }

    @Test
    public void modernFormSuccessTest(){
        //Arrange
        menu.goToPage("forms");
        FormPage form = new FormPage(page);
        form.getInputField("name").fill("Test User");
        form.getInputField("email").fill("testuser@accesshq.com");
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
        menu.goToPage("forms");
        FormPage form = new FormPage(page);

        //Act
        form.clickSubmit();

        //Assert
        Assertions.assertTrue(page.locator("text=Your name is required").isVisible());
        Assertions.assertTrue(page.locator("text=Your email is required").isVisible());
        Assertions.assertTrue(page.locator("text=You must agree to continue").isVisible());
    }

    @Test
    public void testTraditionalForm(){
        //Arrange
        menu.goToPage("forms");
        FormPage form = new FormPage(page);
        form.clickFormType("Traditional");

        //Act
        form.getInputField("address").fill("sample address");
        form.selectGender("Male");
        form.clickAllow();
        form.clickReset();

        //Assert
        Assertions.assertEquals("", form.getInputField("address").innerText());
        Assertions.assertEquals("M", page.locator("select#gender").inputValue());

    }
    @AfterEach
    public void tearDown(){
        browser.close();
    }
}
