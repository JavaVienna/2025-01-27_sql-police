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

  private String thingyId;

  private String groupId;

  public InQueryEntity() {

  }

  public InQueryEntity(final Long id, final String thingyId, final String groupId) {
    this.id = id;
    this.thingyId = thingyId;
    this.groupId = groupId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getThingyId() {
    return thingyId;
  }

  public void setThingyId(String thingyId) {
    this.thingyId = thingyId;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }
}
