package com.example.demo3;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Name API")
@RequestMapping("/v1")
public class NameController {

    @Autowired
    NameService service;
    @Operation(summary = "Get a name inserted", description = "Returns a name as per the name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The name was not found")
    })
    @GetMapping(path = "/name")
    public ResponseEntity<String> loadName(@RequestParam @Parameter(name = "Gabriel", description = "name inserted in reverse", example = "leirbaG") String name) {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.get(name));
    }
    @Operation(summary = "Reverse a name", description = "Returns a given name in reverse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully reversed"),
            @ApiResponse(responseCode = "404", description = "The name was not reversible")
    })
    @PostMapping("/name/reversedName")
    public ResponseEntity<String> reverseName(@RequestParam @Parameter(name = "name", description = "Product id", example = "1") String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.reverse(name));
    }
}
