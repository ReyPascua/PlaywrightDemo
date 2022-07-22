package com.accesshq.Models;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

public class PlanetPage {
    Page page;
    public PlanetPage(Page page){
        this.page = page;
    }

    public void clickEarth(){
        page.locator("text=EarthDistance from the sun149,600,000 kmRadius6,371 kmExplore >> button").click();
    }

    public void clickNeptune(){
        page.locator("text=NeptuneDistance from the sun4,495,000 kmRadius24,622 kmExplore >> button").click();
    }

    public Long getFarthestDistance() {
        Long distance = Long.valueOf(0);
        for (ElementHandle s : page.querySelectorAll(".distance")) {
            Long formatDistance = Long.parseLong(s.innerHTML().replaceAll("\\D", ""));
            if (formatDistance > distance) {
                distance = formatDistance;
            }
        }
        return distance;
    }
}
