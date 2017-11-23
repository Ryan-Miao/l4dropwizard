package com.test.domain.resource;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created by Ryan Miao on 11/23/17.
 */
public class LocalResourceTest {
    @Test
    public void getIndex() throws Exception {
        Locale locale = Locale.forLanguageTag("zh-CN");

        assertEquals("zh", locale.getLanguage());
        assertEquals("CN", locale.getCountry());
    }

}