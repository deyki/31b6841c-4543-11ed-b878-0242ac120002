package deyki.Covid19MicroService.service;

import deyki.Covid19MicroService.error.CountryNotFoundException;
import deyki.Covid19MicroService.model.CountryResponseModel;

import java.io.IOException;

public interface Covid19MicroService {

    void downloadData() throws IOException;

    CountryResponseModel getCountryByCountryCode(String countryCode) throws CountryNotFoundException;
}
