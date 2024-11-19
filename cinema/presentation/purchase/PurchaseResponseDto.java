package cinema.presentation.purchase;

import cinema.bl.Ticket;

public record PurchaseResponseDto(String token, Ticket ticket) {

}
