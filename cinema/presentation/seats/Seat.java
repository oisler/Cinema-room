package cinema.presentation.seats;

public record Seat(int row, int column, int price) {

  public static Seat convert(cinema.bl.Seat seat) {
    return new Seat(seat.getRow(), seat.getColumn(), seat.getPrice());
  }

}
