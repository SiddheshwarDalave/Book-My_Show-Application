package com.examplespringbootproject.BookMyShow.Service.impl;

import com.examplespringbootproject.BookMyShow.ConverterOrAdapter.TheatreConverter;
import com.examplespringbootproject.BookMyShow.DTO.EntryDto.TheatreEntryDto;
import com.examplespringbootproject.BookMyShow.DTO.ResponseDto.TheatreResponseDto;
import com.examplespringbootproject.BookMyShow.DTO.TheatreDto;
import com.examplespringbootproject.BookMyShow.Enums.SeatTypes;
import com.examplespringbootproject.BookMyShow.Model.TheatreEntity;
import com.examplespringbootproject.BookMyShow.Model.TheatreSeatEntity;
import com.examplespringbootproject.BookMyShow.Repository.TheatreRepository;
import com.examplespringbootproject.BookMyShow.Repository.TheatreSeatsRepository;
import com.examplespringbootproject.BookMyShow.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TheatreServiceImpl implements TheatreService {
    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    TheatreSeatsRepository theatreSeatsRepository; //creates seats here only
    @Override
    public TheatreEntryDto addTheatre(TheatreEntryDto theatreEntryDto) {
        //create seat list
        TheatreEntity theatreEntity= TheatreConverter.convertDtoToTheatreEntity(theatreEntryDto);

        //for seat
        List<TheatreSeatEntity> seats = createTheaterSeatsFunc();

        //set theatreId for each seat
        for(TheatreSeatEntity theatreSeatEntity1:seats){
            theatreSeatEntity1.setTheatreEntity(theatreEntity);
        }
        theatreRepository.save(theatreEntity);
        return theatreEntryDto;
    }
    List<TheatreSeatEntity> createTheaterSeatsFunc(){

        List<TheatreSeatEntity> seats=new ArrayList<>();

        seats.add(getTheatreSeat("1A",100, SeatTypes.CLASSIC));
        seats.add(getTheatreSeat("1B",100, SeatTypes.CLASSIC));
        seats.add(getTheatreSeat("1C",100, SeatTypes.CLASSIC));
        seats.add(getTheatreSeat("1D",100, SeatTypes.CLASSIC));

        seats.add(getTheatreSeat("2A",100, SeatTypes.PREMIUM));
        seats.add(getTheatreSeat("2B",100, SeatTypes.PREMIUM));
        seats.add(getTheatreSeat("2C",100, SeatTypes.PREMIUM));
        seats.add(getTheatreSeat("2D",100, SeatTypes.PREMIUM));

        theatreSeatsRepository.saveAll(seats);
       return seats;
    }
    TheatreSeatEntity getTheatreSeat(String seat_no,int rate,SeatTypes seatType){
        return TheatreSeatEntity.builder().seat_no(seat_no).rate(rate).seat_type(seatType).build();
        //not->>// return TheatreSeatEntity.builder().seat_no(theatreSeatEntity.getSeat_no()).rate(theatreSeatEntity.getRate()).seat_type(theatreSeatEntity.getSeat_type()).build();
    }

    @Override
    public TheatreResponseDto getTheatre(int id) {
        TheatreEntity theatreEntity=theatreRepository.findById(id).get();
        TheatreResponseDto theatreResponseDto=TheatreConverter.convertTheatreEntityToDto(theatreEntity);
        return theatreResponseDto;
    }
}
