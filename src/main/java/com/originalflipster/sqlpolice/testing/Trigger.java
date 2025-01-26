package com.originalflipster.sqlpolice.testing;

import net.ttddyy.dsproxy.QueryCountHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public void createSurrogateEntities(@RequestParam(name = "count", required = false) Integer count) {
    final int seCount = count != null ? count : 500;
    QueryCountHolder.clear();
    StopWatch watch = new StopWatch();
    watch.start();
    service.createSurrogateEntities(seCount);
    watch.stop();
    log.info("Created {} Entities with surrogate key, using {} queries in {} ms", seCount, QueryCountHolder.getGrandTotal().getTotal(), watch.getTotalTimeMillis());
  }

  @GetMapping(value = "/non-surrogate")
  @ResponseStatus(HttpStatus.OK)
  public void createNonSurrogateEntities(@RequestParam(name = "count", required = false) Integer count) {
    final int seCount = count != null ? count : 500;
    QueryCountHolder.clear();
    StopWatch watch = new StopWatch();
    watch.start();
    service.createNonSurrogateEntities(seCount);
    watch.stop();
    log.info("Created {} Entities with unique id, using {} queries in {} ms", seCount, QueryCountHolder.getGrandTotal().getTotal(), watch.getTotalTimeMillis());
  }

  @GetMapping(value = "/non-surrogate-version")
  @ResponseStatus(HttpStatus.OK)
  public void createNonSurrogateWithVersionEntities(@RequestParam(name = "count", required = false) Integer count) {
    final int seCount = count != null ? count : 500;
    QueryCountHolder.clear();
    StopWatch watch = new StopWatch();
    watch.start();
    service.createNonSurrogateWithVersionEntities(seCount);
    watch.stop();
    log.info("Created {} Entities with unique id & version, using {} queries in {} ms", seCount, QueryCountHolder.getGrandTotal().getTotal(), watch.getTotalTimeMillis());
  }

  @GetMapping(value = "/in-clause")
  @ResponseStatus(HttpStatus.OK)
  public void queryWithTupleInClause(@RequestParam(name = "count", required = false) Integer count) {
    final int seCount = count != null ? count : 500;
    QueryCountHolder.clear();
    StopWatch watch = new StopWatch();
    watch.start();
    service.queryWithTupleInClause(seCount);
    watch.stop();
    log.info("Queried {} Entities with in tuple where, using {} queries in {} ms", seCount, QueryCountHolder.getGrandTotal().getTotal(), watch.getTotalTimeMillis());
  }
}
