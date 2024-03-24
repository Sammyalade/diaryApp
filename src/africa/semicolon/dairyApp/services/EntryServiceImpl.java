package africa.semicolon.dairyApp.services;

import africa.semicolon.dairyApp.datas.models.Entry;
import africa.semicolon.dairyApp.datas.repositories.EntryRepository;
import africa.semicolon.dairyApp.dtos.EntryCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryServiceImpl implements EntryService{

    @Autowired
    private EntryRepository entryRepository;

    @Override
    public Entry create(EntryCreationRequest entryCreationRequest) {
        Entry newEntry = new Entry();
        newEntry.setAuthor(entryCreationRequest.getUsername());
        newEntry.setTitle(entryCreationRequest.getTitle());
        newEntry.setBody(entryCreationRequest.getBody());
        return entryRepository.save(newEntry);
    }

    @Override
    public List<Entry> getAllEntries(String username) {
        List<Entry> result = new ArrayList<>();
        for(Entry entry: entryRepository.findAll()) {
            if (entry.getAuthor().equals(username.toLowerCase())){
                result.add(entry);
            }
        }
        return result;
    }

    @Override
    public Entry checkEntryBy(String title) {
        return entryRepository.findByTitle(title);
    }


    @Override
    public void delete(String title) {
        entryRepository.delete(entryRepository.findByTitle(title));
    }

    @Override
    public void delete(Entry entry) {
        entryRepository.delete(entry);
    }

    @Override
    public void removeAllEntries() {
        entryRepository.deleteAll();
    }

}
