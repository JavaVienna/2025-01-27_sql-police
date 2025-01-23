package com.originalflipster.sqlpolice;

import com.originalflipster.sqlpolice.in.CompoundFilter;
import com.originalflipster.sqlpolice.in.InQueryEntity;
import com.originalflipster.sqlpolice.in.InQueryRepo;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("local")
public class SqlPoliceTest {

  @Autowired
  private SurrogateRepo repo;

  @Autowired
  private RealNonSurrogateRepo nonSurrRepo;

  @Autowired
  private InQueryRepo inQueryRepo;

  @BeforeEach
  void setup() {
    QueryCountHolder.clear();
  }

  @Test
  void create1000SurrogateEntities() {
    repo.saveAllAndFlush(IntStream.range(0, 500).mapToObj(it -> new SurrogateEntity()).collect(Collectors.toSet()));

    QueryCount count = QueryCountHolder.getGrandTotal();
    System.out.println(count.getTotal());
  }

  @Test
  void create1000NonSurrogateEntities() {
    nonSurrRepo.saveAll(IntStream.range(0, 1000).mapToObj(it -> new RealNonSurrogateEntity(UUID.randomUUID().toString())).collect(Collectors.toSet()));

    nonSurrRepo.findAll();
  }

  @Test
  void queryWithTupleInClause() {
    inQueryRepo.saveAll(IntStream.range(0, 1000).mapToObj(it -> new InQueryEntity(Long.valueOf(it), new CompoundFilter(String.valueOf(it), String.valueOf(it)))).collect(
        Collectors.toSet()));

    inQueryRepo.findAllByCompoundIn(Set.of(new CompoundFilter("5", "5"), new CompoundFilter("78", "78"), new CompoundFilter("100", "100")));
  }
}
