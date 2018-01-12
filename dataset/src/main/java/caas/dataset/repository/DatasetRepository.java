package caas.dataset.repository;

import caas.dataset.entity.Dataset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatasetRepository extends MongoRepository<Dataset, Integer> {
    public Dataset findByWorkspaceIdAndName(Integer workspaceId, String name);

    public Boolean existsByWorkspaceIdAndName(Integer workspaceId, String name);

    public Boolean existsByNameAndIsPublic(String name, Boolean isPublic);

    public List<Dataset> findAllByWorkspaceId(Integer workspaceId);

    public List<Dataset> findAllByIsPublic(Boolean isPublic);
}