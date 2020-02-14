package io.github.mat3e;

public class HelloService {

    static final String FALLBACK_NAME = "world";
    String prepareGreeting(String name){
        return "Hello " + (name != null ? name: FALLBACK_NAME);
    }
}
