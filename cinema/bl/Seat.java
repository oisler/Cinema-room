package cinema.bl;

public class Seat {

  private static final int SEAT_PRICE = 8;
  private static final int PRIMARY_SEAT_PRICE = 10;
  private static final int PRICE_BOARDER = 4;

  private final int number;
  private final int row;
  private final int column;
  private final int price;
  private boolean isOccupied;
  private String token;

  public Seat(int number, int row, int column) {
    this.number = number;
    this.row = row;
    this.column = column;
    this.price = determinePrice(row);
    setOccupied(false);
  }

  private static int determinePrice(int row) {
    return row <= PRICE_BOARDER ? PRIMARY_SEAT_PRICE : SEAT_PRICE;
  }

  public int getNumber() {
    return number;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public int getPrice() {
    return price;
  }

  public boolean isOccupied() {
    return isOccupied;
  }

  public void setOccupied(boolean isOccupied) {
    this.isOccupied = isOccupied;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

}
