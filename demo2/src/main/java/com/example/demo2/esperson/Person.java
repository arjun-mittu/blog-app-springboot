package com.example.demo2.esperson;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "person")
public class Person {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type=FieldType.Text)
    private String name;
}
