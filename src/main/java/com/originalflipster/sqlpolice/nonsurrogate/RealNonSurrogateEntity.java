package com.originalflipster.sqlpolice.nonsurrogate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RealNonSurrogateEntity extends NonSurrogateEntity{

  public RealNonSurrogateEntity() {}

  public RealNonSurrogateEntity(final String id) {
    this.id = id;
  }

  @Id
  private String id;

  @Override
  public String getId() {
    return id;
  }
}
