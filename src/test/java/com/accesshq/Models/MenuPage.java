package com.accesshq.Models;

import com.microsoft.playwright.*;

public class MenuPage {
    Page page;
    public MenuPage(Page page) { this.page = page; }

    public void selectMenu(String input){
        page.navigate("https://d18u5zoaatmpxx.cloudfront.net/#/");
        page.locator("text=personHomeFormsPlanets >> [aria-label=" + input + "]").click();
    }
}
