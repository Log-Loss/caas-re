package caas.poc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Job {
    @Id
    @GeneratedValue
    public Integer id;

    public Integer modelId;

    public Integer datasetId;

    public String datasetName;

    public String type;

    public Integer epochs;

    public Integer batchSize;

    @Column(columnDefinition = "TEXT")
    public String config;

    @Column(columnDefinition = "TEXT")
    public String message;

    @Column(columnDefinition = "LONGTEXT")
    public String state;

    public Date createTime;

    public Date startTime;

    public Date finishTime;

    public Double accuracy;
}
