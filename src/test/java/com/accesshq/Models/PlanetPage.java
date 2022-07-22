package com.accesshq.Models;
import com.microsoft.playwright.Page;

public class PlanetPage {
    Page page;
    public PlanetPage(Page page){
        this.page = page;
    }

    public void clickEarth(){
        page.locator("text=EarthDistance from the sun149,600,000 kmRadius6,371 kmExplore >> button").click();
    }

}
