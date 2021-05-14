package com.springboot.history.app.controller;

import com.springboot.history.app.model.dto.BuildHistoryDTO;
import com.springboot.history.app.model.entity.BuildHistory;
import com.springboot.history.app.repository.BuildHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

import static com.springboot.history.app.Constants.*;

@RestController
public class BuildHistoryResource {

    @Autowired
    private BuildHistoryRepository buildHistoryRepository;

    @GetMapping("/buildHistory/{id}")
    @ResponseBody
    public BuildHistory findById(@PathVariable("id") String id) {
        return buildHistoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Not Found"));
    }

    @GetMapping("/buildHistory")
    @ResponseBody
    public List<BuildHistory> getAll() {
        return buildHistoryRepository.findAll();
    }

    @GetMapping("/buildHistory/sortByField")
    public List<BuildHistory> sortByField(@RequestParam String field) {
        if ((field.equals(USER)) || (field.equals(RESULT)) || (field.equals(BUILDNAME)) || (field.equals(ARTIFACTTYPE))) {
            return buildHistoryRepository.findAll(Sort.by(field));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field Not Found");

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
        if (buildHistoryRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Not Found");
        else
            buildHistoryRepository.deleteById(id);
    }
}

