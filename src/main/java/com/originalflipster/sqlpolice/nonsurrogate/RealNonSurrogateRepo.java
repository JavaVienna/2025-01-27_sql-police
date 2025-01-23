package com.originalflipster.sqlpolice.nonsurrogate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RealNonSurrogateRepo extends JpaRepository<RealNonSurrogateEntity, String> {
}
