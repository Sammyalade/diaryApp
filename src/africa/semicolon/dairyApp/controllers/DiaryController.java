package africa.semicolon.dairyApp.controllers;

import africa.semicolon.dairyApp.services.DiaryService;
import africa.semicolon.dairyApp.services.DiaryServiceImpl;
import africa.semicolon.dairyApp.services.EntryService;
import africa.semicolon.dairyApp.services.EntryServiceImpl;
import dtos.EntryCreationRequest;
import dtos.EntryDeleteRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;
import exceptions.DiaryAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private EntryService entryService;

    @PostMapping("/diary/register")
    public String registerUser(@RequestBody RegisterRequest registerRequest){
        try {
            diaryService.register(registerRequest);
            return "Registration Successful";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    @PostMapping("/diary/login")
    public String login(LoginRequest loginRequest){
        try {
            diaryService.login(loginRequest);
            return "Login Successful";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String logout(String username){
        try{
            diaryService.logout(username);
            return "Thank you for using our app";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String createEntry(EntryCreationRequest entryCreationRequest){
        try{
            return diaryService.createEntry(entryCreationRequest).toString();
        } catch(DiaryAppException e){
            return e.getMessage();
        }
    }

    public String getAllEntriesBy(String username){
        try {
            return entryService.getAllEntries(username).toString();
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String deleteEntry(EntryDeleteRequest entryDeleteRequest) {
        try {
            diaryService.deleteEntry(entryDeleteRequest);
            return "Entry successfully deleted";
        } catch (DiaryAppException e){
            return e. getMessage();
        }
    }
    public void changePassword(LoginRequest loginRequest){
        diaryService.changePassword(loginRequest);
    }
}
