package com.accesshq.Models;

import com.microsoft.playwright.*;

public class MenuBar {
    private final Page page;
    public MenuBar(Page page) { this.page = page; }

    public void goToPage(String input){
        page.locator("text=personHomeFormsPlanets >> [aria-label=" + input + "]").click();
    }

}
