package com.greglturnquist.hackingspringboot.reactive.domain;

import lombok.*;

@Getter
@Setter
@ToString
public class Dish {
    private String description;
    private boolean delivered = false;

    public static Dish deliver(Dish dish) {
        Dish deliveredDish = new Dish(dish.description);
        deliveredDish.delivered = true;

        return deliveredDish;
    }

    public Dish(String description) {
        this.description = description;
    }
}
