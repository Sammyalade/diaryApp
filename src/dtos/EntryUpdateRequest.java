package dtos;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntryUpdateRequest {

    private String id;
    private String title;
    private String body;
    private String username;
}
