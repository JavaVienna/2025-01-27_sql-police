package com.originalflipster.sqlpolice.nonsurrogate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class NonSurrogateEntityWithVersion {

  public NonSurrogateEntityWithVersion() {

  }

  public NonSurrogateEntityWithVersion(final String name) {
    this.name = name;
  }

  @Id
  private String name;

  @Version
  private int version = 0;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getVersion() {
    return version;
  }

  protected void setVersion(int version) {
    this.version = version;
  }
}
