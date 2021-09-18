package com.example.translator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
class TranslatorFileServiceTest {
@Autowired
    private TranslatorFileService translatorFileService;
    @Test
    void isInvert() throws IOException {
        boolean translator = false;
        translator = translatorFileService.isInvert();
        assertTrue(translator);


    }
}