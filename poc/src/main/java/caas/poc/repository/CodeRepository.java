package caas.poc.repository;

import caas.poc.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepository extends JpaRepository<Code, Integer> {

    public List<Code> findAllByWorkspaceId(Integer workspaceId);
}