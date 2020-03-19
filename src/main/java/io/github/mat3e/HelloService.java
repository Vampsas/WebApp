package io.github.mat3e;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class HelloService {
    static final Lang FALLBACK_LANG = new Lang(1L, "Hello", "en");
    static final String FALLBACK_NAME = "world";
    private final Logger logger = LoggerFactory.getLogger(HelloService.class);

    private LangRepository repository;

    HelloService(){
        this(new LangRepository());
    }
    HelloService(LangRepository repository){
        this.repository = repository;
    }

    String prepareGreeting(String name, String lang){
        Long langId;
        try {
            langId = Optional.ofNullable(lang).map(Long::valueOf).orElse((FALLBACK_LANG.getId()));
        }catch (NumberFormatException e){
            logger.warn("Non-numeric language is used: " + lang);
            langId = FALLBACK_LANG.getId();
        }

        var welcomeMsg = repository.findByID(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMsg + " " + nameToWelcome;
    }
}
