package datas.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Entry {

    @Id
    private String id;
    private String title;
    private String body;
    private String author;
}
