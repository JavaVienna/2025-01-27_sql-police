package com.originalflipster.sqlpolice.nonsurrogate;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import org.springframework.data.domain.Persistable;

public abstract class NonSurrogateEntity<T> implements Persistable<T> {

  @Transient
  private boolean newEntity = true;

  @Override
  public boolean isNew() {
    return newEntity;
  }

  @PrePersist
  @PostLoad
  public void markNotNew() {
    this.newEntity = false;
  }
}
