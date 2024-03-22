package services;

import africa.semicolon.dairyApp.datas.models.Diary;
import africa.semicolon.dairyApp.datas.models.Entry;
import africa.semicolon.dairyApp.datas.repositories.DiaryRepository;
import africa.semicolon.dairyApp.services.DiaryService;
import africa.semicolon.dairyApp.services.EntryService;
import africa.semicolon.dairyApp.services.EntryServiceImpl;
import dtos.EntryCreationRequest;
import dtos.EntryUpdateRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;
import exceptions.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiaryServiceImpl implements DiaryService {
    private static DiaryRepository repository;
    private final EntryService entryService = new EntryServiceImpl();


    @Override
    public Diary register(RegisterRequest registerRequest) {
        if(registerRequest.getUsername().isEmpty() || registerRequest.getPassword().isEmpty())
            throw new EmptyStringException("Username or Password cannot be empty");
        if(repository.findById(registerRequest.getUsername()) == null) {
            return createDiary(registerRequest);
        }
        throw new UsernameTakenException("Username is taken");
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Optional<Diary> diaryToFind = findUser(loginRequest.getUsername());
        validatePassword(diaryToFind.get().getPassword(), loginRequest.getPassword());
        diaryToFind.get().setLocked(false);
        repository.save(diaryToFind.get());
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
    public void updateEntry(EntryUpdateRequest entryUpdateRequest) {
        Entry entry = entryService.checkEntryBy(entryUpdateRequest.getId());
        if (entry == null) {
            throw new NoSuchEntryException("No such entry attached to this user");
        }
        entryService.update(entryUpdateRequest);
    }

    @Override
    public void getAllEntries(String username) {
        findUser(username);
        entryService.getAllEntries("username");
    }

    @Override
    public Entry checkEntryById(String username, long id) {
        findUser(username);
        Entry entry = entryService.checkEntryBy(id);
        if(entry.getAuthor().equals(username)){
            return entry;
        }
        throw new NoSuchEntryException("No such entry");
    }

    @Override
    public void removeAllDiaries() {
        repository.deleteAll();
    }


    @Override
    public void deleteEntry(String username, long id) {
        findUser(username);
        entryService.delete(entryService.checkEntryBy(id));
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
        newDiary.setUsername(registerRequest.getUsername());
        newDiary.setPassword(registerRequest.getPassword());
        return repository.save(newDiary);
    }
}
