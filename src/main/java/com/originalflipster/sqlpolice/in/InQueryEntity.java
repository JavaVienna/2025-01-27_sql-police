package com.originalflipster.sqlpolice.in;

import com.originalflipster.sqlpolice.nonsurrogate.NonSurrogateEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "in_query_entity")
public class InQueryEntity extends NonSurrogateEntity<Long> {

  @Id
  private Long id;

  @Embedded
  private CompoundFilter compound;

  public InQueryEntity() {

  }

  public InQueryEntity(final Long id, final CompoundFilter compound) {
    this.id = id;
    this.compound = compound;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CompoundFilter getCompound() {
    return compound;
  }

  public void setCompound(CompoundFilter compound) {
    this.compound = compound;
  }
}
