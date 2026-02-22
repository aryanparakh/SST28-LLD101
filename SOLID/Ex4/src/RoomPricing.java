import java.util.Map;

public class RoomPricing {

    private static final Map<Integer, PricingComponent> ROOMS = Map.of(
            LegacyRoomTypes.SINGLE, new FixedRoom(14000, 5000),
            LegacyRoomTypes.DOUBLE, new FixedRoom(15000, 5000),
            LegacyRoomTypes.TRIPLE, new FixedRoom(12000, 5000),
            LegacyRoomTypes.DELUXE, new FixedRoom(16000, 5000)
    );

    public static PricingComponent of(int roomType) {
        return ROOMS.get(roomType);
    }

    private static class FixedRoom implements PricingComponent {
        private final Money monthly;
        private final Money deposit;

        FixedRoom(double monthly, double deposit) {
            this.monthly = new Money(monthly);
            this.deposit = new Money(deposit);
        }

        public Money monthly() { return monthly; }
        public Money deposit() { return deposit; }
    }
}
