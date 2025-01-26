package com.originalflipster.sqlpolice.testing;

import net.ttddyy.dsproxy.QueryCountHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Trigger {

  private static final Logger log = LoggerFactory.getLogger(Trigger.class);

  private final SeService service;

  public Trigger(final SeService service) {
    this.service = service;
  }

  @GetMapping(value = "/surrogate")
  @ResponseStatus(HttpStatus.OK)
  public void create1000SurrogateEntities() {
    QueryCountHolder.clear();
    service.create1000SurrogateEntities();
    log.info("Total Queries executed: {}", QueryCountHolder.getGrandTotal().getTotal());
  }

  @GetMapping(value = "/non-surrogate")
  @ResponseStatus(HttpStatus.OK)
  public void create1000NonSurrogateEntities() {
    QueryCountHolder.clear();
    service.create1000NonSurrogateEntities();
    log.info("Total Queries executed: {}", QueryCountHolder.getGrandTotal().getTotal());
  }

  @GetMapping(value = "/non-surrogate-version")
  @ResponseStatus(HttpStatus.OK)
  public void create1000NonSurrogateWithVersionEntities() {
    QueryCountHolder.clear();
    service.create1000NonSurrogateWithVersionEntities();
    log.info("Total Queries executed: {}", QueryCountHolder.getGrandTotal().getTotal());
  }

  @GetMapping(value = "/in-clause")
  @ResponseStatus(HttpStatus.OK)
  public void queryWithTupleInClause() {
    QueryCountHolder.clear();
    service.queryWithTupleInClause();
    log.info("Total Queries executed: {}", QueryCountHolder.getGrandTotal().getTotal());
  }
}
