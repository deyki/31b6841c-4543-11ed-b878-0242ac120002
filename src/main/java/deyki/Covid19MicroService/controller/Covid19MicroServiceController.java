package deyki.Covid19MicroService.controller;

import deyki.Covid19MicroService.error.CountryNotFoundException;
import deyki.Covid19MicroService.model.CountryResponseModel;
import deyki.Covid19MicroService.service.impl.Covid19MicroServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class Covid19MicroServiceController {

    private final Covid19MicroServiceImpl service;

    public Covid19MicroServiceController(Covid19MicroServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/download-data")
    public ResponseEntity<String> downloadData() throws IOException {

        service.downloadData();

        return ResponseEntity.status(HttpStatus.OK).body("Ready...");
    }

    @GetMapping("/country/{countryCode}")
    public ResponseEntity<CountryResponseModel> getCountryByCountryCode(@PathVariable String countryCode) throws CountryNotFoundException {

        return ResponseEntity.status(HttpStatus.OK).body(service.getCountryByCountryCode(countryCode));
    }
}
