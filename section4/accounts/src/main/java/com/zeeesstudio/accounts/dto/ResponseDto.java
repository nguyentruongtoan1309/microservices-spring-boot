package com.zeeesstudio.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
@AllArgsConstructor
public class ResponseDto {
    @Schema(
            description = "Status code in the response"
    )
    private String statusCode;

    @Schema(
            description = "Schema to hold successful response information"
    )
    private String statusMsg;
}
