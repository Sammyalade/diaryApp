package africa.semicolon.dairyApp.controllers;

import africa.semicolon.dairyApp.services.DiaryService;
import africa.semicolon.dairyApp.services.EntryService;
import dtos.EntryCreationRequest;
import dtos.EntryDeleteRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;
import exceptions.DiaryAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String login(@RequestBody LoginRequest loginRequest){
        try {
            diaryService.login(loginRequest);
            return "Login Successful";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    @PatchMapping("/logout/{name}")
    public String logout(@PathVariable("name") String username){
        try{
            diaryService.logout(username);
            return "Thank you for using our app";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    @PostMapping("diary/createEntry")
    public String createEntry(@RequestBody EntryCreationRequest entryCreationRequest){
        try{
            return diaryService.createEntry(entryCreationRequest).toString();
        } catch(DiaryAppException e){
            return e.getMessage();
        }
    }

    @GetMapping("diary/getAllEntries/{name}")
    public String getAllEntriesBy(@PathVariable("name") String username){
        try {
            return entryService.getAllEntries(username).toString();
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    @PostMapping("/diary/deleteEntry")
    public String deleteEntry(@RequestBody EntryDeleteRequest entryDeleteRequest) {
        try {
            diaryService.deleteEntry(entryDeleteRequest);
            return "Entry successfully deleted";
        } catch (DiaryAppException e){
            return e. getMessage();
        }
    }

    @PatchMapping("/diary/changePassword/{newPassword}")
    public void changePassword(@PathVariable("newPassword") LoginRequest loginRequest){
        diaryService.changePassword(loginRequest);
    }
}
