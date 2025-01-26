package com.originalflipster.sqlpolice.nonsurrogate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NonSurrogateEntityWithVersionRepo extends JpaRepository<NonSurrogateEntityWithVersion, String>  {
}
