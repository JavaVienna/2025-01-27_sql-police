package com.originalflipster.sqlpolice.surrogate;

import com.originalflipster.sqlpolice.surrogate.SurrogateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurrogateRepo extends JpaRepository<SurrogateEntity, Long> {
}
