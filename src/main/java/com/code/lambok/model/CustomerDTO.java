package com.code.lambok.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import tools.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonDeserialize(builder = CustomerDTO.CustomerDTOBuilder.class)
@Data
@Builder
public class CustomerDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    @NotBlank
    @NotNull
    private String name;

    @JsonProperty("version")
    private Integer version;

    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}