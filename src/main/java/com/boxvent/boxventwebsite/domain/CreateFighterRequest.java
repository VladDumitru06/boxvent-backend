package com.boxvent.boxventwebsite.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFighterRequest {
    @Length(min = 2)
    @NotNull
    private String name;
    @NotNull
    private  Long wins;
    @NotNull
    private  Long draws;
    @NotNull
    private  Long loses;
    @NotBlank
    private String image;
}
