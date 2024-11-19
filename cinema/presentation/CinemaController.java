package cinema.presentation;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cinema.bl.CinemaService;
import cinema.bl.Statistics;
import cinema.bl.Ticket;
import cinema.presentation.purchase.PurchaseRequestDto;
import cinema.presentation.purchase.PurchaseResponseDto;
import cinema.presentation.returnTicket.ReturnRequestDto;
import cinema.presentation.returnTicket.ReturnResponseDto;
import cinema.presentation.seats.Seat;
import cinema.presentation.seats.SeatsResponseDto;
import cinema.presentation.stats.StatisticRequestResponse;

@RestController
public class CinemaController {

  @Autowired
  private CinemaService cinemaService;

  @GetMapping("/seats")
  public ResponseEntity<SeatsResponseDto> getSeats() {
    var seats = cinemaService.getSeats()
        .stream()
        .map(Seat::convert)
        .toList();

    return ResponseEntity.ok().body(new SeatsResponseDto(9, 9, seats));
  }

  @GetMapping("/stats")
  public ResponseEntity<StatisticRequestResponse> getStats(@RequestParam(required = false) String password) {
    Statistics statistics = cinemaService.getStatistics(password);
    return ResponseEntity.ok().body(new StatisticRequestResponse(statistics.income(), statistics.available(), statistics.purchased()));
  }

  @PostMapping("/purchase")
  public ResponseEntity<PurchaseResponseDto> purchaseSeat(@Valid @RequestBody PurchaseRequestDto request) {
    var seat = cinemaService.purchaseSeat(request.row(), request.column());
    Ticket ticket = new Ticket(seat.getRow(), seat.getColumn(), seat.getPrice());
    return ResponseEntity.ok().body(new PurchaseResponseDto(seat.getToken(), ticket));
  }

  @PostMapping("/return")
  public ResponseEntity<ReturnResponseDto> returnSeat(@RequestBody ReturnRequestDto request) {
    var seat = cinemaService.returnSeat(request.token());
    Ticket ticket = new Ticket(seat.getRow(), seat.getColumn(), seat.getPrice());
    return ResponseEntity.ok().body(new ReturnResponseDto(ticket));
  }

}
