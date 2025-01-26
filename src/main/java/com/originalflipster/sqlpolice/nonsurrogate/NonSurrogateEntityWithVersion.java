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
  private Integer version;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getVersion() {
    return version;
  }

  protected void setVersion(Integer version) {
    this.version = version;
  }
}
