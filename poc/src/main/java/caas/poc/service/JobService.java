package caas.poc.service;

import caas.poc.entity.Job;
import caas.poc.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public Job create(Integer modelId, Integer datasetId, String config, String type, String datasetName, Integer epochs, Integer batchSize) {
        Job job = new Job();
        job.modelId = modelId;
        job.config = config;
        job.type = type;
        job.datasetId = datasetId;
        job.datasetName = datasetName;
        job.createTime = new Date();
        job.epochs = epochs;
        job.batchSize = batchSize;
        jobRepository.saveAndFlush(job);
        return job;
    }

    public Job start(Integer id) throws Exception {
        Job job = jobRepository.findOne(id);
        job.startTime = new Date();
        job.finishTime = null;
        job.accuracy = null;

        Map<String, java.io.Serializable> body = new HashMap<>();
        body.put("conf", job.config);
        body.put("type", job.type);
        body.put("datasetName", job.datasetName);
        body.put("batchSize", job.batchSize);
        body.put("epochs", job.epochs);
        body.put("jobId", job.id);
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<Map> requestEntity = new HttpEntity<>(body, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        Thread train = new Thread(() -> {
            restTemplate.exchange("http://localhost:10002/train", HttpMethod.POST, requestEntity, String.class);
        });
        train.start();
        jobRepository.saveAndFlush(job);
        return job;
    }

    public void remove(Integer id) {
        jobRepository.delete(id);
    }

    public void removeAll() {
        jobRepository.deleteAll();
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Boolean exists(Integer id) {
        return jobRepository.exists(id);
    }

    public Object findOne(Integer id) {
        return jobRepository.findOne(id);
    }

    public Job update(Integer id, Integer epochs, Integer batchSize) {
        Job job = jobRepository.findOne(id);
        job.epochs = epochs;
        job.batchSize = batchSize;
        jobRepository.saveAndFlush(job);
        return job;
    }

    public Job update(Integer id, String state, Double accuracy, Date finishTime, String message) {
        Job job = jobRepository.findOne(id);
        job.accuracy = accuracy;
        job.state = state;
        job.finishTime = finishTime;
        job.message = message;
        jobRepository.saveAndFlush(job);
        return job;
    }

    public Object findAllByModelId(Integer modelId) {
        return jobRepository.findAllByModelId(modelId);
    }
}
