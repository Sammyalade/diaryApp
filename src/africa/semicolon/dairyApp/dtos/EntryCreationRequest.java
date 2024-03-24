package africa.semicolon.dairyApp.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntryCreationRequest {

    private String title;
    private String body;
    private String username;
}
