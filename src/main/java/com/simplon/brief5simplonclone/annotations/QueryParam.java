package com.simplon.brief5simplonclone.annotations;

public @interface QueryParam {
    String value();

    String defaultValue() default "";
}
