package caas.poc.repository;

import caas.poc.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    public List<Model> findAllByWorkspaceId(Integer workspaceId);

    public Boolean existsByWorkspaceIdAndName(Integer workspaceId, String name);
}