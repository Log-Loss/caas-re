package caas.poc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Dataset {
    @Id
    @GeneratedValue
    Integer id;

    public String name;

    public String hash;

    public Boolean isPublic = false;

    public Integer workspaceId;
}
