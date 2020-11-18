package com.todo.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ValidationMessageServiceImpl implements ValidationMessageService {

    private final MessageSource messageSource;

    @Autowired
    public ValidationMessageServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String id) {
        return messageSource.getMessage(id, null, LocaleContextHolder.getLocale());
    }
}