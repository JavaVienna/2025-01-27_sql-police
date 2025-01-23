package com.originalflipster.sqlpolice.nonsurrogate;

import com.originalflipster.sqlpolice.nonsurrogate.NonSurrogateEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RealNonSurrogateEntity extends NonSurrogateEntity<String> {

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
