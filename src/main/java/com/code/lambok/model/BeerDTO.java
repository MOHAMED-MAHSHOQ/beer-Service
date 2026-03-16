package com.code.lambok.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonDeserialize(builder = BeerDTO.BeerDTOBuilder.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BeerDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("version")
    private Integer version;

    @JsonProperty("beerName")
    private String beerName;

    @JsonProperty("beerStyle")
    private BeerStyle beerStyle;

    @JsonProperty("upc")
    private String upc;

    @JsonProperty("quantityOnHand")
    private Integer quantityOnHand;

    @JsonProperty("price")
    private BigDecimal price;

    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}


