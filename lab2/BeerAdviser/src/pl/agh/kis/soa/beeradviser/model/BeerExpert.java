package pl.agh.kis.soa.beeradviser.model;

import java.util.HashMap;
import java.util.Map;

public class BeerExpert {
    private Map<String, String> recommendedBeers = new HashMap<>();

    public BeerExpert()
    {
        recommendedBeers.put("straw", "Piwo1");
        recommendedBeers.put("yellow", "Piwo2");
        recommendedBeers.put("darkGold", "Piwo3");
        recommendedBeers.put("lightBrown", "Piwo4");
        recommendedBeers.put("brown", "Piwo5");
        recommendedBeers.put("darkBrown", "Piwo6");
    }

    public String getRecommendedBeer(String beerColor)
    {
        return recommendedBeers.get(beerColor);
    }
}


