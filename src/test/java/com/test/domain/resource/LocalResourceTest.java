package com.test.domain.resource;

import com.miao.easyi18n.support.ResourceBundleMessageSource;
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

    @Test
    public void testI18n(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames("i18n/messages2", "i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");

        String index = messageSource.getMessage("index", null, Locale.US);
        System.out.println(index);

    }

}