package com.controller;

import com.domain.dto.RentDto;
import com.exceptions.NoCopiesAvailableException;
import com.exceptions.RentNotFoundException;
import com.service.facade.DatabasesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/V1/rents")
public class RentedGameController {
    @Autowired
    DatabasesFacade facade;

    @GetMapping(value = "getAllRents")
    public List<RentDto> getAllRentedGames() {
        return facade.getAllRentedGames();
    }

    @GetMapping(value = "getRent")
    public RentDto getRentedGame(@RequestParam Long gameId) throws RentNotFoundException {
        return facade.getRentedGame(gameId);
    }

    @PutMapping(value = "updateRent")
    public RentDto updateRentedGame(@RequestBody RentDto rentDto) throws NoCopiesAvailableException {
        return facade.updateRentedGame(rentDto);}

    @PostMapping(value = "createRent")
    public void createRentedGame(@RequestBody RentDto rentDto) throws NoCopiesAvailableException {
           facade.createRentedGame(rentDto);
    }

    @DeleteMapping(value = "deleteRent")
    public void deleteRentedGame(@RequestParam Long gameId) throws RentNotFoundException{
        facade.deleteRentedGame(gameId);
    }
}
