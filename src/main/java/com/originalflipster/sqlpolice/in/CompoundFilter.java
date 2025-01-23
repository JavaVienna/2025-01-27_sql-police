package com.originalflipster.sqlpolice.in;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CompoundFilter implements Serializable {

  private String thingyId;

  private String groupId;

  public CompoundFilter() {}

  public CompoundFilter(final String thingyId, final String groupId) {
    this.thingyId = thingyId;
    this.groupId = groupId;
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
