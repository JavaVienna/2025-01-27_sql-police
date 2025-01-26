package com.originalflipster.sqlpolice.surrogate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SurrogateRepo extends JpaRepository<SurrogateEntity, Long> {
}
