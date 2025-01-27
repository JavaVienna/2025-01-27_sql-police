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

  public void queryWithTupleInClause(final int count) {
    inQueryRepo.saveAll(IntStream.range(0, count).mapToObj(it -> new InQueryEntity(Long.valueOf(it), String.valueOf(it), String.valueOf(it))).collect(
        Collectors.toSet()));

    inQueryRepo.findAllByCompoundIn(
        Set.of(new CompoundFilter("5", "5"), new CompoundFilter("78", "78"), new CompoundFilter("100", "100")).stream().map(it -> it.toString()).collect(
            Collectors.toSet()));
  }
}
