package com.accesshq.Models;
import com.microsoft.playwright.ElementHandle;

public class PlanetCard {
    ElementHandle card;

    public PlanetCard(ElementHandle card){
        this.card = card;
    }

    public String getPlanetName(){
     return card.querySelector("h2").innerText();
    }

    public Long getPlanetDistance(){
        return Long.parseLong(card.querySelector(".distance").innerText()
                .replaceAll("\\D", ""));
    }

    public void clickExploreButton(){
        card.querySelector("button:has-text(\"Explore\")").click();
    }
}
