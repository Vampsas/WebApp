package io.github.mat3e;

import org.junit.Test;

import java.awt.*;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {

    private static final String WELCOME = "Hello";
    private static final String FALLBACK_ID_WELCOME = "Hola";

    @Test
    public void test_nullName_prepareGreeting_returnsFallbackValue() {
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, "-1");

        //then
        assertEquals(WELCOME + " " +  HelloService.FALLBACK_NAME, result);
    }
    @Test
    public void test_nullLang_prepareGreeting_returnsFallbackIdLang() {
        //given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, null);

        //then
        assertEquals(FALLBACK_ID_WELCOME + " " +  HelloService.FALLBACK_NAME, result);
    }
    @Test
    public void test_nonExistingLang_prepareGreeting_returnsWelcomeMsg() {
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository); 
        // when
        var result = SUT.prepareGreeting(null, "-1");

        //then
        assertEquals( HelloService.FALLBACK_LANG.getWelcomeMsg() + " " +  HelloService.FALLBACK_NAME, result);
    }
    @Test
    public void test_textLang_prepareGreeting_returnsFallbackIdLang() {
        //given

        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, "abc");

        //then
        assertEquals(FALLBACK_ID_WELCOME + " " +  HelloService.FALLBACK_NAME, result);
    }

    private LangRepository fallbackLangIdRepository(){
        return new LangRepository(){
            @Override
            Optional<Lang> findByID(Long id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())){
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        String name = "test";
        // when
        var result = SUT.prepareGreeting(name,"-1");

        //then
        assertEquals(WELCOME + " " + name, result);
    }


    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findByID(Long id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
}