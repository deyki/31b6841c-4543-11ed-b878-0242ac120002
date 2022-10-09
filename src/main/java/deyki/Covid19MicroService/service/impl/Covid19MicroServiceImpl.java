package deyki.Covid19MicroService.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import deyki.Covid19MicroService.entity.Country;
import deyki.Covid19MicroService.error.CountryNotFoundException;
import deyki.Covid19MicroService.model.CountryResponseModel;
import deyki.Covid19MicroService.repository.Covid19MicroServiceRepository;
import deyki.Covid19MicroService.service.Covid19MicroService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;


@Service
public class Covid19MicroServiceImpl implements Covid19MicroService {

    private final Covid19MicroServiceRepository covid19MicroServiceRepository;
    private final ModelMapper modelMapper;

    public Covid19MicroServiceImpl(Covid19MicroServiceRepository covid19MicroServiceRepo, ModelMapper modelMapper) {
        this.covid19MicroServiceRepository = covid19MicroServiceRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void downloadData() throws IOException {

        URL url = new URL("https://api.covid19api.com/summary");

        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(url).get("Countries");

        for (JsonNode jsonNode : arrayNode) {

            Country country = new Country();
            country.setID(jsonNode.get("ID").asLong());
            country.setCountry(jsonNode.get("Country").textValue());
            country.setCountryCode(jsonNode.get("CountryCode").textValue());
            country.setNewConfirmed(jsonNode.get("NewConfirmed").asInt());
            country.setNewDeaths(jsonNode.get("NewDeaths").asInt());
            country.setNewRecovered(jsonNode.get("NewRecovered").asInt());
            country.setSlug(jsonNode.get("Slug").textValue());
            country.setTotalConfirmed(jsonNode.get("TotalConfirmed").asInt());
            country.setTotalDeaths(jsonNode.get("TotalDeaths").asInt());
            country.setTotalRecovered(jsonNode.get("TotalRecovered").asInt());
            country.setDate(jsonNode.get("Date").textValue());
            country.setPremium(jsonNode.get("Premium").textValue());

            covid19MicroServiceRepository.save(country);
        }
    }

    @Override
    public CountryResponseModel getCountryByCountryCode(String countryCode) throws CountryNotFoundException {

        return covid19MicroServiceRepository
                .findByCountryCode(countryCode)
                .map(country -> modelMapper.map(country, CountryResponseModel.class))
                .orElseThrow(() -> new CountryNotFoundException(String.format("Country with code: %s not found!", countryCode)));
    }
}
