package pl.sda.crm.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestEntity {
    @Id
    long id;
}