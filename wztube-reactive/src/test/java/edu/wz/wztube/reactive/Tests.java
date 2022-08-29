package edu.wz.wztube.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Tests {
    @Test
    public void blah() throws InterruptedException {
        Mono<Long> time = Mono.defer(() -> Mono.just(System.currentTimeMillis()));
    
        time.subscribe(System.out::println);
        Thread.sleep(1000);
        time.subscribe(System.out::println);
    }
    
    @Test
    public void testSwitchIfEmpty(){
        Mono.just("aa")
                .map(v -> v + " juju")
                .switchIfEmpty(buildError())
                .subscribe(System.out::println);

    }
    
    Mono<String> buildError(){
        System.out.println("buildError");
        return Mono.just("An error occured!"); //<-- evaluated as soon as read
    }
}
