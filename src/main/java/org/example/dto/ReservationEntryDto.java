package org.example.dto;


import lombok.Builder;
import org.example.entity.ReservationEntry;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public class ReservationEntryDto {

    private String bookId;
    private int count;

    public ReservationEntryDto() {

    }
    public ReservationEntryDto(String bookId, int count) {
        this.bookId = bookId;
        this.count = count;
    }

    public static ReservationEntryDto convertToReservationEntryDto(ReservationEntry reservationEntry) {
        return ReservationEntryDto.builder()
                .bookId(reservationEntry.getBookId())
                .count(reservationEntry.getCount())
                .build();
    }
    public static List<ReservationEntryDto> convertToReservationEntryDto(List<ReservationEntry> entries) {
        return entries.stream()
                .map(ReservationEntryDto::convertToReservationEntryDto)
                .collect(Collectors.toList());
    }

    public static ReservationEntry convertToReservationEntry(ReservationEntryDto dto) {
        return ReservationEntry.newEntry(dto.bookId, dto.count);
    }

    public static List<ReservationEntry> convertToReservationEntry(List<ReservationEntryDto> dtos) {
        return dtos.stream()
                .map(ReservationEntryDto::convertToReservationEntry)
                .collect(Collectors.toList());
    }

    public String getBookId() {
        return bookId;
    }

    public int getCount() {
        return count;
    }
}
