package africa.semicolon.dairyApp.datas.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Diary {
    @Id
    private String username;
    private String password;
    private boolean isLocked = true;
}
