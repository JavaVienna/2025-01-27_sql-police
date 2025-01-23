package com.originalflipster.sqlpolice.in;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InQueryRepo extends JpaRepository<InQueryEntity, Long> {

  Collection<InQueryEntity> findAllByCompoundIn(Collection<CompoundFilter> filter);
}
