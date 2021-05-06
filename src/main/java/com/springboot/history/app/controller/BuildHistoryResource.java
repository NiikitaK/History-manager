package com.springboot.history.app.controller;

import com.springboot.history.app.model.dto.BuildHistoryDTO;
import com.springboot.history.app.model.entity.BuildHistory;
import com.springboot.history.app.repository.BuildHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BuildHistoryResource {

    @Autowired
    private BuildHistoryRepository buildHistoryRepository;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/buildHistory/{id}")
    @ResponseBody
    public BuildHistory findById(@PathVariable("id") String id) {
        return buildHistoryRepository.findById(id).get();
    }

    @GetMapping("/buildHistory")
    @ResponseBody
    public List<BuildHistory> getAll() {
        return buildHistoryRepository.findAll();
    }

    @PostMapping("/buildHistory")
    public void createHistory(@RequestBody BuildHistoryDTO buildHistoryDTO) {
        BuildHistory buildHistory = BuildHistoryDTO.builder()
                .date(new Date(System.currentTimeMillis()))
                .user(buildHistoryDTO.getUser())
                .result(buildHistoryDTO.getResult())
                .deployName(buildHistoryDTO.getBuildName())
                .platformType(buildHistoryDTO.getArtifactType())
                .build();
        buildHistoryRepository.save(buildHistoryDTO);
    }

    @DeleteMapping("/buildHistory/{id}")
    public void deleteHistory(@PathVariable("id") String id) {
        buildHistoryRepository.deleteById(id);
    }

}

