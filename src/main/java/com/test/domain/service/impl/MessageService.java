package com.test.domain.service.impl;

import com.miao.easyi18n.support.ResourceBundleMessageSource;
import com.test.domain.service.IMessageService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ryan Miao on 11/23/17.
 */
@Singleton
public class MessageService implements IMessageService{

    private final ResourceBundleMessageSource messageSource;

    @Inject
    public MessageService(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @Override
    public String getMessage(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    @Override
    public String getMessage(String key, List<String> args, Locale locale) {
        return messageSource.getMessage(key, args.toArray(), locale);
    }
}
