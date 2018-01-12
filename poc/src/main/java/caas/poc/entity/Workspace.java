package caas.poc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Workspace {
    @Id
    @GeneratedValue
    public Integer id;

    public String name;

    public Integer userId;
}
