package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ktelabs.test.models.AbstractEntity;
import ru.ktelabs.test.models.dto.AbstractDto;
import ru.ktelabs.test.services.CommonService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract controller for main common methods used in model`s services.
 *
 * @param <E> any inheritor of AbstractEntity.
 * @param <S> service for model.
 * @param <T> dto for model.
 */
public abstract class AbstractController<
        E extends AbstractEntity,
        S extends CommonService<E>,
        T extends AbstractDto> {
    protected final S service;

    protected AbstractController(S service) {
        this.service = service;
    }

    @Operation(summary = "Get all entities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Index is ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))})})
    @GetMapping()
    public abstract ResponseEntity<List<T>> index();

    @Operation(summary = "Create a new entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity is created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AbstractEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Entity already exists",
                    content = @Content)})
    @PostMapping()
    public abstract ResponseEntity<T> create(@RequestBody T newDTO);

    @Operation(summary = "Get entity by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity is found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AbstractDto.class))}),
            @ApiResponse(responseCode = "404", description = "Entity not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<AbstractDto> show(@PathVariable("id") Long id) {
        E found = service.getById(id);
        return ResponseEntity.ok(found.createDTO());
    }

    @Operation(summary = "Update entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity is updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AbstractDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Entity not found",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<AbstractDto> update(@PathVariable Long id,
                                              @RequestBody E newItem) {
        E old = service.getById(id);
        E updated = service.update(old, newItem);
        return ResponseEntity.ok(updated.createDTO());
    }

    @Operation(summary = "Delete entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity is deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Entity not found",
                    content = @Content)})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        boolean delete = service.delete(id);
        return ResponseEntity.ok(delete);
    }
}
