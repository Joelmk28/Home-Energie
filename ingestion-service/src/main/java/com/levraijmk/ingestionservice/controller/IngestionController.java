package com.levraijmk.ingestionservice.controller;

import com.levraijmk.ingestionservice.dto.EnergyUsageDto;
import com.levraijmk.ingestionservice.service.IngestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ingestion")
public class IngestionController {

    private final IngestionService ingestionService;

    public IngestionController(final IngestionService ingestionService){
        this.ingestionService = ingestionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void ingestData(@RequestBody EnergyUsageDto usageDto){
        ingestionService.ingestionEnergyUsage(usageDto);
    }

}
