package caas.poc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Code {
    @Id
    @GeneratedValue
    Integer id;

    public String name;

    public String type = "text";

    public String content;

    public Integer workspaceId;
}
