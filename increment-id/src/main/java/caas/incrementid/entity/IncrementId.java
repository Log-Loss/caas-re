package caas.incrementid.entity;


import org.springframework.data.annotation.Id;


public class IncrementId {
    @Id
    public String name;

    public Integer id = 0;
}
