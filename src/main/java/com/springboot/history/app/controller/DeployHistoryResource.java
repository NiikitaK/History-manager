package com.springboot.history.app.controller;

import com.springboot.history.app.model.entity.DeployHistory;
import com.springboot.history.app.model.dto.DeployHistoryDTO;
import com.springboot.history.app.repository.DeployHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class DeployHistoryResource {

    @Autowired
    private DeployHistoryRepository deployHistoryRepository;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/deployHistory/{id}")
    @ResponseBody
    public DeployHistory findById(@PathVariable("id") String id) {
        return deployHistoryRepository.findById(id).get();
    }

    @GetMapping("/deployHistory")
    @ResponseBody
    public List<DeployHistory> getAll() {
        return deployHistoryRepository.findAll();
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
        deployHistoryRepository.deleteById(id);
    }

}