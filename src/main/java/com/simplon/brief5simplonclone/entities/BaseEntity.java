package com.simplon.brief5simplonclone.entities;

import com.simplon.brief5simplonclone.utils.JSON;

public class BaseEntity {

  @Override
  public String toString() {
    return JSON.stringify(this);
  }
}
