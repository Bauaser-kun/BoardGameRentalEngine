package com.controller;

import com.domain.Rent;
import com.domain.dto.RentDto;
import com.exceptions.NoCopiesAvailableException;
import com.exceptions.RentNotFoundException;
import com.mapper.RentedGamesMapper;
import com.service.RentedGameDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/V1/rentedGames")
public class RentedGameController {
    @Autowired
    RentedGamesMapper mapper;

    @Autowired
    RentedGameDbService dbService;

    @GetMapping(value = "getAllRents")
    public List<RentDto> getAllRentedGames() {
        return mapper.mapToRentedGameDtoList(dbService.getAllRentedGames());
    }

    @GetMapping(value = "getRent")
    public RentDto getRentedGame(@RequestParam Long gameId) throws RentNotFoundException {
        return mapper.mapToRentedGameDto(dbService.getRentedGame(gameId).orElseThrow(RentNotFoundException::new));
    }

    @PutMapping(value = "updateRent")
    public RentDto updateRentedGame(@RequestBody RentDto rentDto) throws NoCopiesAvailableException {
        Rent savedGame = dbService.saveRentedGame(mapper.mapToRentedGame(rentDto));
        return mapper.mapToRentedGameDto(savedGame);
    }

    @PostMapping(value = "createRent")
    public void createRentedGame(@RequestBody RentDto rentDto) throws NoCopiesAvailableException {
           try {
               dbService.saveRentedGame(mapper.mapToRentedGame(rentDto));
           } catch (NoCopiesAvailableException e) {
               throw e;
           }
    }

    @DeleteMapping(value = "deleteRent")
    public void deleteUser(@RequestParam Long gameId) throws RentNotFoundException{
        try {
            dbService.deleteRentedGame(gameId);
        } catch (IllegalArgumentException e) {
            throw new RentNotFoundException();
        }
    }
}
