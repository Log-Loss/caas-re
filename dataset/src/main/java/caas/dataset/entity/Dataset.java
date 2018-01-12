package caas.dataset.entity;


import org.springframework.data.annotation.Id;


public class Dataset {
    @Id
    public Integer id;

    public String name;

    public String md5;

    public Boolean isPublic = false;

    public Integer workspaceId;
}
