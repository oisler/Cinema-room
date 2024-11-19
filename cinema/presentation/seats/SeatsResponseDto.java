package cinema.presentation.seats;

import java.util.List;

public record SeatsResponseDto(int rows, int columns, List<Seat> seats) {

}
