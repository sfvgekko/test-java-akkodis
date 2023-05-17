package com.akkodis.testjava.content.infrastructure.controller;

import com.akkodis.testjava.content.application.GetProductsUseCase;
import com.akkodis.testjava.content.application.mapper.ProductMapper;
import com.akkodis.testjava.content.infrastructure.controller.dto.ProductFilters;
import com.akkodis.testjava.content.infrastructure.controller.dto.ProductOutputDto;
import com.akkodis.testjava.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class GetProductsController {


    private final GetProductsUseCase useCase;

    private final ProductMapper mapper;


    @GetMapping
    @Operation(summary = "Get Product Application Price",
               description = "Return the product price application according the filters with the price list, currency," +
                       "and price, and applying, if applicable, the priority.",
               responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                         @Content(mediaType = "application/json",
                                 array = @ArraySchema(schema = @Schema(implementation = ProductOutputDto.class)))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    )
               }
    )
    public ProductOutputDto getProductApplicationPrice(
            @Parameter(name = "brandId", description = "Group brand identifier", example = "1")
            @RequestParam(required = false) Integer brandId,
            @Parameter(name = "productId", description = "Product identifier", example = "35455")
            @RequestParam(required = false) Integer productId,
            @Parameter(name = "applicationDate", description = "Application date in format yyyy-MM-dd HH:mm:ss",
                    example = "2020-06-14 10:00:00")
            @RequestParam(required = false) String applicationDate) {

        ProductFilters filters = new ProductFilters(brandId, productId, applicationDate);

        return mapper.domainToOutputDto(useCase.getProductApplicationPrice(filters));

    }
}
