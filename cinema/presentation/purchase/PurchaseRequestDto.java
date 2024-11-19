package cinema.presentation.purchase;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record PurchaseRequestDto(
    @Min(1) @Max(9) int row,
    @Min(1) @Max(9) int column) {
}
