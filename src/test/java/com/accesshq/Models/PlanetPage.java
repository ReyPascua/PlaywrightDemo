package com.accesshq.Models;

import com.microsoft.playwright.*;
import java.io.FileNotFoundException;
import java.util.List;

public class PlanetPage {
    private final Page page;
    private List<ElementHandle> getAllPlanets;
    public PlanetPage(Page page){
        this.page = page;
        this.getAllPlanets = page.querySelectorAll(".planet");
    }

    public ElementHandle getPlanetCard(String planetName) throws FileNotFoundException {
        for(ElementHandle p: getAllPlanets){
            String title = p.querySelector("h2").innerText();
            if(title.equalsIgnoreCase(planetName)){
                return p;
            }
        }
        throw new FileNotFoundException();
    }

    public void clickExploreButton(String planetTitle) throws FileNotFoundException {
         getPlanetCard(planetTitle).querySelector("\"Explore\"").click();
    }

    public String getTitle(String planetTitle) throws FileNotFoundException {
        return getPlanetCard(planetTitle).querySelector(".name.headline.primary--text").innerHTML();
    }

    public String getDistance(String planetTitle) throws FileNotFoundException {
        return getPlanetCard(planetTitle).querySelector(".distance").innerHTML().replaceAll("\\D", "");
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
