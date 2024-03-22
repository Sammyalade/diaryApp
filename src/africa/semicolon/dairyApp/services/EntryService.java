package africa.semicolon.dairyApp.services;

import africa.semicolon.dairyApp.datas.models.Entry;
import dtos.EntryCreationRequest;
import dtos.EntryUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EntryService {

    Entry create(EntryCreationRequest newEntry);

    List<Entry> getAllEntries(String username);

    Entry checkEntryBy(String username);
    Optional<Entry> checkEntryBy(long id);
    void delete(long id);

    void delete(Entry entry);

    Entry update(EntryUpdateRequest entryUpdateRequest);

    void removeAllEntries();
}
