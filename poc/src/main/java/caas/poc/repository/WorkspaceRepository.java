package caas.poc.repository;

import caas.poc.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Integer> {

    public List<Workspace> findAllByUserId(Integer userId);

    public Workspace findByUserIdAndName(Integer userId, String name);

    public Boolean existsByUserIdAndName(Integer userId, String name);

}