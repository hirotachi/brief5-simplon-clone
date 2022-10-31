package com.simplon.brief5simplonclone.annotations;

import java.lang.annotation.Retention;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface QueryParam {
    String value();

    String defaultValue() default "";
}
