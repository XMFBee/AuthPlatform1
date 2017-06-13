package com.gs.common.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by GZWangBin on 2017/4/24.
 */
@Retention(RetentionPolicy.RUNTIME)
 @Target(ElementType.TYPE)
 public @interface ModelTitle{
     public String name();
 }