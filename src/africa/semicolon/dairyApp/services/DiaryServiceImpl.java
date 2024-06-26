package africa.semicolon.dairyApp.services;

import africa.semicolon.dairyApp.datas.models.Diary;
import africa.semicolon.dairyApp.datas.models.Entry;
import africa.semicolon.dairyApp.datas.repositories.DiaryRepository;
import africa.semicolon.dairyApp.dtos.*;
import africa.semicolon.dairyApp.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private DiaryRepository repository;
    @Autowired
    private EntryService entryService;


    @Override
    public Diary register(RegisterRequest registerRequest) {
        if (registerRequest.getUsername().trim().isEmpty() || registerRequest.getPassword().isEmpty()) {
            throw new EmptyStringException("Username or Password cannot be empty");
        }
        Optional<Diary> existingDiary = repository.findById(registerRequest.getUsername());
        if (existingDiary.isPresent()) {
            throw new UsernameTakenException("Username is taken");
        }
        return createDiary(registerRequest);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Optional<Diary> diaryToFind = findUser(loginRequest.getUsername().toLowerCase());
        if (diaryToFind.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        Diary diary = diaryToFind.get();
        validatePassword(diary.getPassword(), loginRequest.getPassword());
        diary.setLocked(false);
        repository.save(diary);
    }


    @Override
    public void logout(String username) {
        Optional<Diary> diaryToFind = findUser(username.toLowerCase());
        diaryToFind.get().setLocked(true);
        repository.save(diaryToFind.get());
    }

    @Override
    public void changePassword(LoginRequest loginRequest) {
        Optional<Diary> diaryToChangePassword = findUser(loginRequest.getUsername());
        diaryToChangePassword.get().setPassword(loginRequest.getPassword());
    }






    @Override
    public Entry createEntry(EntryCreationRequest entryCreationRequest) {
        findUser(entryCreationRequest.getUsername());
        return entryService.create(entryCreationRequest);
    }

    @Override
    public void getAllEntries(String username) {
        findUser(username);
        entryService.getAllEntries("username");
    }

    @Override
    public Entry checkEntryByTitle(EntryAccessRequest entryAccessRequest) {
        findUser(entryAccessRequest.getUsername());
        Entry entry = entryService.checkEntryBy(entryAccessRequest.getTitle());
        if(entry.getAuthor().equals(entryAccessRequest.getUsername())){
            return entry;
        }
        throw new NoSuchEntryException("No such entry");
    }

    @Override
    public void removeAllDiaries() {
        repository.deleteAll();
    }


    @Override
    public void deleteEntry(EntryDeleteRequest entryDeleteRequest) {
        findUser(entryDeleteRequest.getUsername());
        entryService.delete(entryService.checkEntryBy(entryDeleteRequest.getTitle()));
    }













    private void validatePassword(String diaryPassword, String userInputPassword) {
        if(!diaryPassword.equals(userInputPassword))
            throw new IncorrectPasswordException("Password is Incorrect");
    }


    private Optional<Diary> findUser(String username) {
        Optional<Diary> diaryToFind = repository.findById(username.toLowerCase());
        checkUserValidity(diaryToFind);
        return diaryToFind;
    }

    private void checkUserValidity(Optional<Diary> diaryToFind) {
        if(diaryToFind.isEmpty()) throw new UserNotFoundException("User not found");
    }

    private Diary createDiary(RegisterRequest registerRequest) {
        Diary newDiary = new Diary();
        newDiary.setUsername(registerRequest.getUsername().toLowerCase());
        newDiary.setPassword(registerRequest.getPassword());
        return repository.save(newDiary);
    }
}
