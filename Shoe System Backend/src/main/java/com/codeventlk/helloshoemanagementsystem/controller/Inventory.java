package com.codeventlk.helloshoemanagementsystem.controller;

import com.codeventlk.helloshoemanagementsystem.Enum.Status;
import com.codeventlk.helloshoemanagementsystem.Util.UtilMatters;
import com.codeventlk.helloshoemanagementsystem.dto.*;
import com.codeventlk.helloshoemanagementsystem.exception.NotFoundException;
import com.codeventlk.helloshoemanagementsystem.service.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/inventory")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class Inventory {
    private final GenderService genderService;
    private final OccasionService occasionService;
    private final VarietyService varietyService;
    private final InventoryService itemService;
    private final SizeService sizeService;

    @GetMapping("/health")
    public String healthCheck(){
        return "Inventory Health Check";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sizeSave")
    public ResponseEntity<?> saveSize(@Validated @RequestBody SizeDTO sizeDTO,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            sizeService.saveSize(sizeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Gender Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Gender saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping("/sizeGetAll")
    public ResponseEntity<?> getAllSizes(){
        try {
            return ResponseEntity.ok(sizeService.getAllSizes());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Size Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/sizeDelete")
    public ResponseEntity<String> deleteSize(@RequestParam String id){
        try {
            sizeService.deleteSize(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Size Details deleted Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Size not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Size Details deleted Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/sizeUpdate")
    public ResponseEntity<String> updateSize(@Validated @RequestBody SizeDTO sizeDTO,
                                               BindingResult bindingResult,
                                               @RequestParam ("id") String id) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            sizeService.updateSize(id,sizeDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Size Details Updated Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Size not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Size Details Gender Unsuccessfully.\nMore Reason\n"+exception);
        }

    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/genderSave")
    public ResponseEntity<?> saveGender(@Validated @RequestBody GenderDTO genderDTO,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            genderService.saveGender(genderDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Gender Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Gender saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping("/genderGetAll")
    public ResponseEntity<?> getAllGenders(){
        try {
            return ResponseEntity.ok(genderService.getAllGenders());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Gender Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/genderDelete")
    public ResponseEntity<String> deleteGender(@RequestParam String id){
        try {
            genderService.deleteGender(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Gender Details deleted Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gender not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Gender Details deleted Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/genderUpdate")
    public ResponseEntity<String> updateGender(@Validated @RequestBody GenderDTO genderDTO,
                                                 BindingResult bindingResult,
                                                 @RequestParam ("id") String id) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            genderService.updateGender(id,genderDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Gender Details Updated Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gender not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Gender Details Gender Unsuccessfully.\nMore Reason\n"+exception);
        }

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/occasionSave")
    public ResponseEntity<?> saveOccasion(@Validated @RequestBody OccasionDTO occasionDTO,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            occasionService.saveOccasion(occasionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Occasion Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Occasion saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping("/occasionGetAll")
    public ResponseEntity<?> getAllOccasion(){
        try {
            return ResponseEntity.ok(occasionService.getAllOccasion());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Occasion Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/occasionDelete")
    public ResponseEntity<String> deleteOccasion(@RequestParam String id){
        try {
            occasionService.deleteOccasion(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Occasion Details deleted Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Occasion not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Occasion Details deleted Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/occasionUpdate")
    public ResponseEntity<String> updateOccasion(@Validated @RequestBody OccasionDTO occasionDTO,
                                               BindingResult bindingResult,
                                               @RequestParam ("id") String id) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            occasionService.updateOccasion(id,occasionDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Occasion Details Updated Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Occasion not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Occasion Details Updated Unsuccessfully.\nMore Reason\n"+exception);
        }

    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/varietySave")
    public ResponseEntity<?> saveVariety(@Validated @RequestBody VarietyDTO varietyDTO,
                                         BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            varietyService.saveVariety(varietyDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Variety Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Variety saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping("/varietyGetAll")
    public ResponseEntity<?> getAllVariety(){
        try {
            return ResponseEntity.ok(varietyService.getAllVariety());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Variety Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/varietyDelete")
    public ResponseEntity<String> deleteVariety(@RequestParam String id){
        try {
            varietyService.deleteVariety(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Variety Details deleted Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Variety not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Variety Details deleted Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/varietyUpdate")
    public ResponseEntity<String> updateVariety(@Validated @RequestBody VarietyDTO varietyDTO,
                                                 BindingResult bindingResult,
                                                 @RequestParam ("id") String id) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            varietyService.updateVariety(id,varietyDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Variety Details Updated Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Variety not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Variety Details Updated Unsuccessfully.\nMore Reason\n"+exception);
        }

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveItem(@Valid
                                      @RequestPart ("itemDesc") String itemDesc,
                                      @RequestPart ("pic") String pic,
                                      @RequestParam(value = "genderCode", required = false, defaultValue = "-1") String genderCode,
                                      @RequestParam(value = "occasionCode", required = false, defaultValue = "-1") String occasionCode,
                                      @RequestParam(value = "varietyCode", required = false, defaultValue = "-1") String varietyCode,
                                      Errors errors){
        if (errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemDesc(itemDesc);
        itemDTO.setPic(UtilMatters.convertBase64(pic));
        itemDTO.setGenderCode(genderCode);
        itemDTO.setOccasionCode(occasionCode);
        itemDTO.setVarietyCode(varietyCode);

        try {
            itemService.saveItem(itemDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Item Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Item saved Unsuccessfully.\nMore Details\n"+exception);
        }

    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?> getItem(@PathVariable ("id") String id){
        try {
            return ResponseEntity.ok(itemService.getItem(id));
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Item Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable ("id") String id){
        try {
            itemService.deleteItem(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Item Details deleted Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Item Details deleted Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateItem(@Valid
                                                 @RequestPart ("itemDesc") String itemDesc,
                                                 @RequestPart ("pic") String pic,
                                                 @PathVariable ("id") String id,
                                                 Errors errors) {

        if (errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            pic = UtilMatters.convertBase64(pic);
            itemService.updateItem(id,itemDesc,pic);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Item Details Updated Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Item Details Updated Unsuccessfully.\nMore Reason\n"+exception);
        }

    }

    @GetMapping("/nextSizeId")
    public ResponseEntity<?> getSizeId(){
        try {
            return ResponseEntity.ok(sizeService.getSizeId());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Size Id fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

}
