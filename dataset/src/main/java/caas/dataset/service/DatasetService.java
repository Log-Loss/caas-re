package caas.dataset.service;

import caas.dataset.entity.Dataset;
import caas.dataset.repository.DatasetRepository;
import caas.dataset.util.Hdfs;
import caas.dataset.util.RestCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class DatasetService {
    @Autowired
    private DatasetRepository datasetRepository;


    public Dataset create(Integer workspaceId, String name, Boolean isPublic, byte[] content) throws Exception {
        String md5 = MD5(content);
        Hdfs.putBytes(md5, content);
        Dataset dataset = new Dataset();
        dataset.id = RestCall.idGenerate("dataset");
        dataset.name = name;
        dataset.isPublic = isPublic;
        dataset.md5 = md5;
        dataset.workspaceId = workspaceId;
        datasetRepository.save(dataset);
        return dataset;
    }

    public Dataset setName(Integer id, String name) {
        Dataset dataset = datasetRepository.findOne(id);
        dataset.name = name;
        datasetRepository.save(dataset);
        return dataset;
    }

    public Dataset findOne(Integer id) {
        return datasetRepository.findOne(id);
    }

    public List<Dataset> findAll() {
        return datasetRepository.findAll();
    }

    public List<Dataset> findAllByIsPublic(Boolean isPublic) {
        return datasetRepository.findAllByIsPublic(isPublic);
    }

    public List<Dataset> findAllByIsWorkspaceId(Integer workspaceId) {
        return datasetRepository.findAllByWorkspaceId(workspaceId);
    }

    public Dataset findByWorkspaceIdAndName(Integer workspaceId, String name) {
        return datasetRepository.findByWorkspaceIdAndName(workspaceId, name);
    }

    public Boolean exist(Integer id) {
        return datasetRepository.exists(id);
    }

    public Boolean existsByWorkspaceIdAndName(Integer workspaceId, String name) {
        return datasetRepository.existsByWorkspaceIdAndName(workspaceId, name);
    }

    public Boolean existsByWorkspaceIdAndAndIsPublic(String name, Boolean isPublic) {
        return datasetRepository.existsByNameAndIsPublic(name, isPublic);
    }

    public void remove(Integer id) {
        datasetRepository.delete(id);
    }

    public void removeAll() {
        datasetRepository.deleteAll();
    }

    public Boolean isPublic(Integer id) {
        return datasetRepository.findOne(id).isPublic;
    }

    public byte[] getFile(Integer id) throws Exception {
        Dataset dataset = datasetRepository.findOne(id);
        return Hdfs.getBytes(dataset.md5);
    }


    public String MD5(byte[] data) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data);
            StringBuilder sb = new StringBuilder();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException ignored) {
        }
        return null;
    }

}
