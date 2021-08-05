package com.greglturnquist.hackingspringboot.reactive.service;

import com.greglturnquist.hackingspringboot.reactive.domain.Dish;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class KitchenService {
    private Random picker = new Random();

    public Flux<Dish> getDishes() {
        // delayElements 설정에 따라 250밀리초 마다 연속적으로 generate 된다
        return Flux.<Dish> generate(sink -> sink.next(randomDish())).delayElements(Duration.ofMillis(250));
    }

    private Dish randomDish() {
        return menu.get(picker.nextInt(menu.size()));
    }

    private List<Dish> menu = Arrays.asList(
            new Dish("Sesame chicken"),
            new Dish("Lo mein noodels, plain"),
            new Dish("Sweet & sour beef")
    );
}
