package caas.poc.repository;

import caas.poc.entity.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, Integer> {
    public Dataset findByWorkspaceIdAndName(Integer workspaceId, String name);

    public Boolean existsByWorkspaceIdAndName(Integer workspaceId, String name);

    public Boolean existsByNameAndIsPublic(String name, Boolean isPublic);

    public List<Dataset> findAllByWorkspaceId(Integer workspaceId);

    public List<Dataset> findAllByIsPublic(Boolean isPublic);
}