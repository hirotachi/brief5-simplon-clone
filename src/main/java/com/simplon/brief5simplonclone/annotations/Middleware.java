package com.simplon.brief5simplonclone.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Middleware {
    Class<? extends com.simplon.brief5simplonclone.core.Middleware>[] value();
}
