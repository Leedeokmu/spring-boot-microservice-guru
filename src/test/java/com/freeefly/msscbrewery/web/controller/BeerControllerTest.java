package com.freeefly.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freeefly.msscbrewery.domain.Beer;
import com.freeefly.msscbrewery.repositories.BeerRepository;
import com.freeefly.msscbrewery.web.model.BeerDto;
import com.freeefly.msscbrewery.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "com.freeefly.msscbrewery.web.mappers")
class BeerControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    BeerRepository beerRepository;

    Beer validBeer;

    @BeforeEach
    public void setUp() {
        validBeer = Beer.builder().id(UUID.randomUUID())
                .beerName("Beer1")
                .beerStyle("ALE")
                .upc(123456789012L)
                .build();
    }

    @Test
    void getBeerById() throws Exception {
        given(beerRepository.findById(any(UUID.class))).willReturn(Optional.of(validBeer));
        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);
        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer-get",
                        pathParameters(
                                parameterWithName("beerId").description("UUID of desired beer to get.")
                        ),
                        responseFields(
                                fields.withPath("id").description("Id of Beer").type(UUID.class),
                                fields.withPath("version").description("Version number"),
                                fields.withPath("createdDate").description("Date Created").type(OffsetDateTime.class),
                                fields.withPath("lastModifiedDate").description("Date Updated").type(OffsetDateTime.class),
                                fields.withPath("beerName").description("Beer Name"),
                                fields.withPath("beerStyle").description("Beer Style"),
                                fields.withPath("upc").description("UPC of Beer"),
                                fields.withPath("price").description("Price"),
                                fields.withPath("quantityOnHand").description("Quantity On hand")
                        )
                ));

    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = BeerDto.builder()
                .beerName("new Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(123123789L)
                .price(new BigDecimal(1100))
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockMvc.perform(post("/api/v1/beer")
                .content(beerDtoJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
        .andDo(document("v1/beer-new",
                requestFields(
                        fields.withPath("id").ignored(),
                        fields.withPath("version").ignored(),
                        fields.withPath("createdDate").ignored(),
                        fields.withPath("lastModifiedDate").ignored(),
                        fields.withPath("beerName").description("Name of the beer"),
                        fields.withPath("beerStyle").description("Style of Beer"),
                        fields.withPath("upc").description("Beer UPC").attributes(),
                        fields.withPath("price").description("Beer Price"),
                        fields.withPath("quantityOnHand").ignored()
                )));
    }

    @Test
    void updateBeerById() throws Exception{
        BeerDto beerDto = BeerDto.builder()
                .beerName("update Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(123123789L)
                .price(new BigDecimal(1100))
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .content(beerDtoJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNoContent());
    }

    private static class ConstrainedFields {
        private final ConstraintDescriptions constraintDescriptions;
        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }
        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(
                    key("constraints").value(
                            StringUtils.collectionToDelimitedString(
                                    this.constraintDescriptions.descriptionsForProperty(path), ". ")
                    )
            );
        }
    }
}