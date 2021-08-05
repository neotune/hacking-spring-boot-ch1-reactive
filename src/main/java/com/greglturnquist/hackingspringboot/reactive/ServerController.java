package com.greglturnquist.hackingspringboot.reactive;

import com.greglturnquist.hackingspringboot.reactive.domain.Dish;
import com.greglturnquist.hackingspringboot.reactive.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public class ServerController {
    /*
    스프링 부트 프로젝트 리더인 필웹은 웹 컨트롤러를 가능한 가볍게 가져가는 것을 추천한다.
    웹 컨트롤러는 비즈니스 로직을 담지 말고, 웹 요청 내용을 해석해서 적절한 서비스 함수에 처리를 위임하고
    결과물을 반환하는 역할을 부여하는 편이 좋다.
     */
    private final KitchenService Kitchen;

    @GetMapping(value = "/server", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> serveDishes() {
        /*
        Flux<Dish> 는 전통적인 자바 collection 과는 다른 점이 dish가 비동기적으로 전달된다는 점이다
         */
        return this.Kitchen.getDishes();
    }

    @GetMapping(value = "/served-dishes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> deliverDishes() {
        /*
        Flux<Dish> 는 전통적인 자바 collection 과는 다른 점이 dish가 비동기적으로 전달된다는 점이다
         */
        return this.Kitchen.getDishes().map(dish -> Dish.deliver(dish));
    }
}
