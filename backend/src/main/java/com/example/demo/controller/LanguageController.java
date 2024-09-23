package com.example.demo.controller;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.SuccessResponse;

@RestController
@RequestMapping("/language")
public class LanguageController {

    @PutMapping("/locale")
    public ResponseEntity<?> changeLocale(@RequestParam String language) {
        Locale locale = Locale.forLanguageTag(language);
        LocaleContextHolder.setLocale(locale);
        return ResponseEntity.ok(new SuccessResponse<>(null, "Locale Language Changed"));
    }
}