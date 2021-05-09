package com.springboot.history.app.controller;

import com.springboot.history.app.model.entity.BuildHistory;
import com.springboot.history.app.model.dto.BuildHistoryDTO;
import com.springboot.history.app.repository.BuildHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BuildHistoryResource {

    @Autowired
    private BuildHistoryRepository buildHistoryRepository;


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
    //sort
    @GetMapping("/buildHistory/sort/{field}")
    public List<BuildHistory> sortByField(@PathVariable("field") String field) {
        return buildHistoryRepository.findAll(Sort.by(field));
    }
    @PostMapping("/buildHistory/sortField")
    @ResponseBody
    public List<BuildHistory> sortBuildHistory (@RequestBody String field) {
        return buildHistoryRepository.findAll(Sort.by(field));
    }

    @PostMapping("/buildHistory")
    public void createHistory(@RequestBody BuildHistoryDTO buildHistoryDTO) {
        BuildHistory buildHistory = BuildHistory.builder()
                .date(new Date(System.currentTimeMillis()))
                .user(buildHistoryDTO.getUser())
                .result(buildHistoryDTO.getResult())
                .buildName(buildHistoryDTO.getBuildName())
                .artifactType(buildHistoryDTO.getArtifactType())
                .build();
        buildHistoryRepository.save(buildHistory);
    }

    @DeleteMapping("/buildHistory/{id}")
    public void deleteHistory(@PathVariable("id") String id) {
        buildHistoryRepository.deleteById(id);
    }

}

