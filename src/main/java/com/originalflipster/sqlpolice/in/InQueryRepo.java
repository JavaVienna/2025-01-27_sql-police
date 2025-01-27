package com.originalflipster.sqlpolice.in;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InQueryRepo extends JpaRepository<InQueryEntity, Long> {

  @Query("select iqe from InQueryEntity iqe where concat(iqe.groupId, '-', iqe.thingyId) in ?1")
  Collection<InQueryEntity> findAllByCompoundIn(Collection<String> filter);
}
