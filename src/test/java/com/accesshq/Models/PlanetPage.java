package com.accesshq.Models;

import com.microsoft.playwright.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PlanetPage {
    private final Page page;
    private List<ElementHandle> getAllPlanets;
    public PlanetPage(Page page){
        this.page = page;
        this.getAllPlanets = page.querySelectorAll(".planet");
    }

    public List<PlanetCard> getAllPlanets(){
        ArrayList<PlanetCard> result = new ArrayList<>();
        for(ElementHandle p: getAllPlanets){
            result.add(new PlanetCard(p));
        }
        return  result;
    }

    public PlanetCard getPlanet(Predicate<PlanetCard> logic) throws FileNotFoundException {
        for(PlanetCard p: getAllPlanets()){
            if(logic.test(p)){
                return p;
            }
        }
        throw new FileNotFoundException();
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
