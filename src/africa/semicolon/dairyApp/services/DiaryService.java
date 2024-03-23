package africa.semicolon.dairyApp.services;

import africa.semicolon.dairyApp.datas.models.Diary;
import africa.semicolon.dairyApp.datas.models.Entry;
import dtos.*;
import org.springframework.stereotype.Service;

@Service
public interface DiaryService {

    Diary register(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);
    void logout(String username);
    Entry createEntry(EntryCreationRequest entryCreationRequest);
    void getAllEntries(String username);
    void changePassword(LoginRequest loginRequest);
    void removeAllDiaries();
    Entry checkEntryByTitle(EntryAccessRequest entryAccessRequest);
    void deleteEntry(EntryDeleteRequest entryDeleteRequest);
}
