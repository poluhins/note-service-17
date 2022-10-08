package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.lang.reflect.Field;

@Slf4j
public abstract class Converter<IN, OUT> {

    public abstract OUT to(IN in);

    public abstract IN from(OUT out);

    public static <M> M merge(M original, M edited) {
        for (val editedField : edited.getClass().getDeclaredFields()) {
            Field originalField;
            try {
                originalField = original.getClass()
                        .getDeclaredField(editedField.getName());
            } catch (NoSuchFieldException e) {
                log.error("Cannot find such field ({}) in class {}, casted from {}",
                        editedField.getName(), original.getClass(), edited.getClass());
                continue;
            }

            editedField.setAccessible(true);
            originalField.setAccessible(true);

            try {
                val value = editedField.get(edited);
                if (value != null) {
                    originalField.set(original, value);
                }
            } catch (IllegalAccessException e) {
                log.error("Cannot set value from field ({}) to class {} casted from {}",
                        editedField.getName(), original.getClass(), edited.getClass());
            }
        }
        return original;

    }

}
