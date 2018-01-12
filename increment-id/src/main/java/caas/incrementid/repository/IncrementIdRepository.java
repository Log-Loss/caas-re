package caas.incrementid.repository;

import caas.incrementid.entity.IncrementId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncrementIdRepository extends MongoRepository<IncrementId, String> {
}