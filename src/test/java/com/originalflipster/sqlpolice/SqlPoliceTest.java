package com.originalflipster.sqlpolice;

import com.originalflipster.sqlpolice.in.CompoundFilter;
import com.originalflipster.sqlpolice.in.InQueryEntity;
import com.originalflipster.sqlpolice.in.InQueryRepo;
import com.originalflipster.sqlpolice.nonsurrogate.NonSurrogateEntityWithVersion;
import com.originalflipster.sqlpolice.nonsurrogate.NonSurrogateEntityWithVersionRepo;
import com.originalflipster.sqlpolice.nonsurrogate.RealNonSurrogateEntity;
import com.originalflipster.sqlpolice.nonsurrogate.RealNonSurrogateRepo;
import com.originalflipster.sqlpolice.surrogate.SurrogateEntity;
import com.originalflipster.sqlpolice.surrogate.SurrogateRepo;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.ttddyy.dsproxy.QueryCount;
import net.ttddyy.dsproxy.QueryCountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("local")
public class SqlPoliceTest {

  private static final Logger log = LoggerFactory.getLogger(SqlPoliceTest.class);

  @Autowired
  private SurrogateRepo repo;

  @Autowired
  private RealNonSurrogateRepo nonSurrRepo;

  @Autowired
  private NonSurrogateEntityWithVersionRepo nonSurrVersionRepo;

  @Autowired
  private InQueryRepo inQueryRepo;

  @BeforeEach
  void setup() {
    QueryCountHolder.clear();
  }

  @Test
  void createSurrogateEntities() {
    repo.saveAllAndFlush(IntStream.range(0, 500).mapToObj(it -> new SurrogateEntity()).collect(Collectors.toSet()));

    log.info("Total Queries executed: {}", QueryCountHolder.getGrandTotal().getTotal());
  }

  @Test
  void createNonSurrogateEntities() {
    nonSurrRepo.saveAllAndFlush(IntStream.range(0, 1000).mapToObj(it -> new RealNonSurrogateEntity(UUID.randomUUID().toString())).collect(Collectors.toSet()));

    log.info("Total Queries executed: {}", QueryCountHolder.getGrandTotal().getTotal());
  }

  @Test
  void createNonSurrogateWithVersionEntities() {
    nonSurrVersionRepo.saveAllAndFlush(IntStream.range(0, 1000).mapToObj(it -> new NonSurrogateEntityWithVersion(UUID.randomUUID().toString())).collect(Collectors.toSet()));

    log.info("Total Queries executed: {}", QueryCountHolder.getGrandTotal().getTotal());
  }

  @Test
  void queryWithTupleInClause() {
    inQueryRepo.saveAllAndFlush(IntStream.range(0, 1000).mapToObj(it -> new InQueryEntity(Long.valueOf(it), new CompoundFilter(String.valueOf(it), String.valueOf(it)))).collect(
        Collectors.toSet()));

    inQueryRepo.findAllByCompoundIn(Set.of(new CompoundFilter("5", "5"), new CompoundFilter("78", "78"), new CompoundFilter("100", "100")));

    log.info("Total Queries executed: {}", QueryCountHolder.getGrandTotal().getTotal());
  }
}
