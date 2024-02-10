package com.codex.tomobilina.dtos;

import java.sql.Timestamp;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @NotEmpty
    private Date dtn;
    
    @NotEmpty
    private int sexe;
    
    @NotEmpty
    private char[] password;
    

}
