package com.test.domain.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

/**
 * The Message translation service
 * Created by Ryan Miao on 11/23/17.
 */
public interface IMessageService {

    /**
     * Get translation by message key.
     *
     * @param key The message key in the properties
     * @return the translated message
     */
    String getMessage(String key, Locale locale);

    /**
     * Get translation by message key and compose it with variables.
     * Note that the variable would be injected by {@link MessageFormat}
     *
     * @param key The message key in the properties
     * @param args The variables to inject into the message.
     * @return the translated message.
     */
    String getMessage(String key, List<String> args, Locale locale);
}
