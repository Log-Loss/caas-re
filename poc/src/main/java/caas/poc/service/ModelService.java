package caas.poc.service;

import caas.poc.entity.Model;
import caas.poc.repository.DatasetRepository;
import caas.poc.repository.ModelRepository;
import caas.poc.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private DatasetRepository datasetRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    public Model create(Integer workspaceId, String name) {
        Model model = new Model();
        model.workspaceId = workspaceId;
        model.name = name;
        modelRepository.saveAndFlush(model);
        return model;
    }

    public Model update(Integer id, String name, String config, String datasetName,String type) {
        Model model = modelRepository.findOne(id);
        if (name != null) {
            model.name = name;
        }
        if (config != null) {
            model.config = config;
        }
        if (datasetName != null) {
            model.datasetName = datasetName;
        }
        if (type != null) {
            model.type = type;
        }
        modelRepository.saveAndFlush(model);
        return model;
    }

    public Model setName(Integer id, String name) {
        Model model = modelRepository.findOne(id);
        model.name = name;
        modelRepository.saveAndFlush(model);
        return model;
    }

    public Model setConfig(Integer id, String config) {
        Model model = modelRepository.findOne(id);
        model.config = config;
        modelRepository.saveAndFlush(model);
        return model;
    }

    public Model setDataset(Integer id, Integer datasetId) {
        Model model = modelRepository.findOne(id);
        model.datasetId = datasetId;
        modelRepository.saveAndFlush(model);
        return model;
    }

    public Model removeDataset(Integer id) {
        Model model = modelRepository.findOne(id);
        model.datasetId = null;
        modelRepository.saveAndFlush(model);
        return model;
    }

    public Model findOne(Integer id) {
        return modelRepository.findOne(id);
    }

    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    public List<Model> findAllByWorkspaceId(Integer workspaceId) {
        return modelRepository.findAllByWorkspaceId(workspaceId);
    }

    public Boolean exists(Integer id) {
        return modelRepository.exists(id);
    }

    public Boolean existsByWorkspaceIdAndName(Integer workspaceId, String name) {
        return modelRepository.existsByWorkspaceIdAndName(workspaceId, name);
    }

    public Boolean existsDataset(Integer id, Integer datasetId) {
        return datasetRepository.findOne(datasetId).isPublic ||
                modelRepository.findOne(id).workspaceId.equals(datasetRepository.findOne(datasetId).workspaceId);
    }

    public void remove(Integer id) {
        modelRepository.delete(id);
    }

    public void removeAll() {
        modelRepository.deleteAll();
    }
}
