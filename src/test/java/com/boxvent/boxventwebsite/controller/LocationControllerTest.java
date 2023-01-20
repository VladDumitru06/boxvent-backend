package com.boxvent.boxventwebsite.controller;

import com.boxvent.boxventwebsite.business.*;
import com.boxvent.boxventwebsite.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LocationController.class)
class LocationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreateLocationUseCase createLocationUseCaseMock;
    @MockBean
    private CreateCountryUseCase createCountryUseCaseMock;
    @MockBean
    private CreateCityUseCase createCityUseCaseMock;
    @MockBean
    private GetCitiesUseCase getCitiesUseCaseMock;
    @MockBean
    private GetCountriesUseCase getCountriesUseCaseMock;

    @MockBean
    private AccessTokenDecoder accessTokenDecoder;


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createLocation_shouldReturn200ResponseWithLocation() throws Exception {
        // Arrange
        CreateLocationRequest request = CreateLocationRequest.builder()
                .cityName("Amsterdam")
                .countryCode("NL")
                .build();
        CreateLocationResponse expectedResponse = CreateLocationResponse.builder()
                .locationId(1L)
                        .build();
        when(createLocationUseCaseMock.createLocation(request)).thenReturn(expectedResponse);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/locations")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
        verify(createLocationUseCaseMock).createLocation(request);
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createCity_shouldReturn200ResponseWithCity() throws Exception {
        // Arrange
        CreateCityRequest request = CreateCityRequest.builder()
                .cityName("Amsterdam")
                .countryCode("NL")
                .build();
        CreateCityResponse expectedResponse = CreateCityResponse.builder()
                .cityId(1L)
                .build();
        when(createCityUseCaseMock.createCity(request)).thenReturn(expectedResponse);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/locations/city")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
        verify(createCityUseCaseMock).createCity(request);
    }
            @Test
            @WithMockUser(username = "admin", roles = {"ADMIN"})
            void createCountry_shouldReturn200ResponseWithCountry()throws Exception{
            // Arrange
            CreateCountryRequest request=CreateCountryRequest.builder()
            .countryName("Amsterdam")
            .countryCode("NL")
            .build();
            CreateCountryResponse expectedResponse=CreateCountryResponse.builder()
                    .countryId(1L)
            .build();
            when(createCountryUseCaseMock.createCountry(request)).thenReturn(expectedResponse);

            // Act and Assert
            mockMvc.perform(MockMvcRequestBuilders.post("/locations/country")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
            verify(createCountryUseCaseMock).createCountry(request);
            }

            @Test
            @WithMockUser(username = "admin", roles = {"ADMIN", "CLIENT", "GUEST"})
    void getAllCities_shouldReturn200ResponseWithCitiesArray() throws Exception {
        // Arrange
        City city1 = City.builder()
                .city_name("Amsterdam")
                .country_code("NL")
                .build();
        City city2 = City.builder()
                .city_name("Sofia")
                .country_code("BG")
                .build();
        GetAllCitiesResponse expectedResponse = GetAllCitiesResponse.builder()
                .cities(List.of(city1, city2))
                .build();
        when(getCitiesUseCaseMock.getCities()).thenReturn(expectedResponse);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/locations/cities"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
        verify(getCitiesUseCaseMock).getCities();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "CLIENT", "GUEST"})
    void getAllCountries_shouldReturn200ResponseWithCountriesArray() throws Exception {
        // Arrange
        Country country1 = Country.builder()
                .country_name("Netherlands")
                .country_code("NL")
                .build();
        Country country2 = Country.builder()
                .country_name("Bulgaria")
                .country_code("BG")
                .build();
        GetAllCountriesResponse expectedResponse = GetAllCountriesResponse.builder()
                .countries(List.of(country1, country2))
                .build();
        when(getCountriesUseCaseMock.getCountries()).thenReturn(expectedResponse);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/locations/countries"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
        verify(getCountriesUseCaseMock).getCountries();
    }
}