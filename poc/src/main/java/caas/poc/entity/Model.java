package caas.poc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Model {
    @Id
    @GeneratedValue
    public Integer id;

    public String name;

    public String type;

    @Column(columnDefinition = "TEXT")
    public String config;

    public Integer workspaceId;

    public Integer datasetId;

    public String datasetName;

}
