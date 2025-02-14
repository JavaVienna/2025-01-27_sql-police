package com.originalflipster.sqlpolice.testing;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SeService {

  private final SurrogateRepo repo;
  private final RealNonSurrogateRepo nonSurrRepo;
  private final NonSurrogateEntityWithVersionRepo nonSurrVersionRepo;
  private final InQueryRepo inQueryRepo;

  public SeService(SurrogateRepo repo, RealNonSurrogateRepo nonSurrRepo, NonSurrogateEntityWithVersionRepo nonSurrVersionRepo,
                   InQueryRepo inQueryRepo) {
    this.repo = repo;
    this.nonSurrRepo = nonSurrRepo;
    this.nonSurrVersionRepo = nonSurrVersionRepo;
    this.inQueryRepo = inQueryRepo;
  }

  public void createSurrogateEntities(final int count) {
    repo.saveAll(IntStream.range(0, count).mapToObj(it -> new SurrogateEntity()).collect(Collectors.toSet()));
  }

  public void createNonSurrogateEntities(final int count) {
    nonSurrRepo.saveAll(IntStream.range(0, count).mapToObj(it -> new RealNonSurrogateEntity(UUID.randomUUID().toString())).collect(Collectors.toSet()));
  }

  public void createNonSurrogateWithVersionEntities(final int count) {
    nonSurrVersionRepo.saveAll(IntStream.range(0, count).mapToObj(it -> new NonSurrogateEntityWithVersion(UUID.randomUUID().toString())).collect(Collectors.toSet()));
  }

  public void initTupleInClause(final int count) {
    final int currCount = (int) inQueryRepo.count();
    inQueryRepo.saveAll(IntStream.range(currCount, currCount + count).mapToObj(it -> new InQueryEntity( new CompoundFilter(String.valueOf(it), String.valueOf(it)))).collect(
        Collectors.toSet()));
  }

  public void queryWithTupleInClause(final int count) {
    inQueryRepo.findAllByCompoundIn(IntStream.range(0, count).mapToObj(it -> new CompoundFilter(String.valueOf(it), String.valueOf(it))).collect(
        Collectors.toSet()));
  }
}
