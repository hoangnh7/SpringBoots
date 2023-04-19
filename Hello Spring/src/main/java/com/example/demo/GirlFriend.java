package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class GirlFriend {
    public Outfit outfit;
    public GirlFriend(Outfit outfit){
        this.outfit = outfit;
    }
    public void setOutfit(Outfit outfit) {
        this.outfit = outfit;
    }

    public Outfit getOutfit() {
        return this.outfit;
    }
}
