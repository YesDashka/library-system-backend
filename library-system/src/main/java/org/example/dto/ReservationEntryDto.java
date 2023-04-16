package org.example.dto;


import lombok.Builder;
import org.example.entity.ReservationEntry;

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

    public static ReservationEntry convertToReservationEntry(ReservationEntryDto dto) {
        return ReservationEntry.newEntry(dto.bookId, dto.count);
    }

    public String getBookId() {
        return bookId;
    }

    public int getCount() {
        return count;
    }
}
