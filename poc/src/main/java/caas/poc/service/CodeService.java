package caas.poc.service;

import caas.poc.entity.Code;
import caas.poc.repository.CodeRepository;
import caas.poc.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {
    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    public Code create(Integer workspaceId, String name) {
        Code code = new Code();
        code.workspaceId = workspaceId;
        code.name = name;
        codeRepository.saveAndFlush(code);
        return code;
    }

    public Code findOne(Integer id) {
        return codeRepository.findOne(id);
    }

    public void removeAll() {
        codeRepository.deleteAll();
    }

    public List<Code> getList(Integer workspaceId) {
        return codeRepository.findAllByWorkspaceId(workspaceId);
    }
}
