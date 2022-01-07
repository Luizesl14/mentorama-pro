package com.systemhospital.resource;

import com.systemhospital.service.ProfissionaisMedicineService;
import com.systemhospital.entitiesDto.ProfessionalMedicineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value = "/professional")
public class ProfissionaisMedicineResource {

    @Autowired
    private ProfissionaisMedicineService profService;

    @RolesAllowed("admin")
    @GetMapping
    public ResponseEntity<List<ProfessionalMedicineDto>> findall(@RequestParam("page") Integer page,
                                                                 @RequestParam("pageSize") Integer pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(this.profService.profissionalFindAll(page, pageSize));
    }

    @RolesAllowed("admin")
    @GetMapping(value = "/id")
    public ResponseEntity<ProfessionalMedicineDto> findaById(@PathVariable Integer id ){
        return ResponseEntity.status(HttpStatus.OK).body(this.profService.profissionalFindById(id));
    }

    @RolesAllowed("admin")
    @PostMapping(value = "/new-profissional")
    public ResponseEntity<ProfessionalMedicineDto> saveProfissional(@RequestBody ProfessionalMedicineDto profissionalDto){
        return this.profService.saveProfissional(profissionalDto);
    }

    @RolesAllowed("admin")
    @PutMapping( value = "/update-profissional", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessionalMedicineDto> updateProfissional(@RequestBody ProfessionalMedicineDto profissionalDto){
        return this.profService.saveProfissional(profissionalDto);
    }

    @RolesAllowed("admin")
    @DeleteMapping(value = "/id")
    public ResponseEntity deleteProfissional(@PathVariable Integer id ){
        this.profService.deletProfissional(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}