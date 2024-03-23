package africa.semicolon.dairyApp.services;

import africa.semicolon.dairyApp.datas.models.Entry;
import dtos.EntryCreationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EntryService {

    Entry create(EntryCreationRequest newEntry);

    List<Entry> getAllEntries(String username);

    Entry checkEntryBy(String title);
    void delete(String title);

    void delete(Entry entry);

    void removeAllEntries();
}
