package cinema.bl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class CinemaService {

  List<Seat> seats = new ArrayList<>();

  @PostConstruct
  public void init() {
    int counter = 1;

    for (int row = 1; row < 10; row++) {
      for (int column = 1; column < 10; column++) {
        seats.add(new Seat(counter, row, column));
        counter++;
      }
    }

  }

  public List<Seat> getSeats() {
    return Collections.unmodifiableList(seats);
  }

  public Seat purchaseSeat(int row, int column) {
    Seat seat = seats
        .stream()
        .filter(s -> s.getRow() == row && s.getColumn() == column && !s.isOccupied())
        .findFirst()
        .orElseThrow(IllegalStateException::new);

    seat.setOccupied(true);
    seat.setToken(UUID.randomUUID().toString());
    return seat;
  }

  public Seat returnSeat(String token) {
    Seat seat = seats
        .stream()
        .filter(s -> s.getToken() != null && s.getToken().equals(token))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);

    seat.setOccupied(false);
    seat.setToken(null);
    return seat;
  }

  public Statistics getStatistics(String password) {
    if (password == null || password.isEmpty()) {
      throw new NullPointerException();
    }

    long purchased = seats.stream().filter(Seat::isOccupied).count();
    long available = seats.stream().filter(s -> !s.isOccupied()).count();
    long income = seats.stream().filter(Seat::isOccupied).mapToLong(Seat::getPrice).sum();

    return new Statistics(income, available, purchased);
  }
}
