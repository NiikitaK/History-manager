package com.springboot.history.app.controller;

import com.springboot.history.app.model.entity.DeployHistory;
import com.springboot.history.app.model.dto.DeployHistoryDTO;
import com.springboot.history.app.repository.DeployHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

import static com.springboot.history.app.Constants.*;

@RestController
public class DeployHistoryResource {

    @Autowired
    private DeployHistoryRepository deployHistoryRepository;


    @GetMapping("/deployHistory/{id}")
    @ResponseBody
    public DeployHistory findById(@PathVariable("id") String id) {
        return deployHistoryRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Not Found"));
    }

    @GetMapping("/deployHistory")
    @ResponseBody
    public List<DeployHistory> getAll() {
        return deployHistoryRepository.findAll();
    }

    @PostMapping("/deployHistory/sortByField")
    public List<DeployHistory> sortByField(@RequestParam String field) {
        if (field.equals(RESULT) || (field.equals(DEPLOYNAME)) || (field.equals(PLATFORMTYPE) || (field.equals(PLATFORMVERSION)))) {
            return deployHistoryRepository.findAll(Sort.by(field));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  "Field  Not Found");
    }

    @PostMapping("/deployHistory")
    public void createHistory(@RequestBody DeployHistoryDTO deployHistoryDTO) {
        DeployHistory deployHistory = DeployHistory.builder()
                .date(new Date(System.currentTimeMillis()))
                .user(deployHistoryDTO.getUser())
                .result(deployHistoryDTO.getResult())
                .deployName(deployHistoryDTO.getDeployName())
                .platformType(deployHistoryDTO.getPlatformType())
                .platformVersion(deployHistoryDTO.getPlatformVersion())
                .build();
        deployHistoryRepository.save(deployHistory);
    }

    @DeleteMapping("/deployHistory/{id}")
    public void deleteHistory(@PathVariable("id") String id) {
        if (deployHistoryRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Not Found");
        else
        deployHistoryRepository.deleteById(id);
    }

}