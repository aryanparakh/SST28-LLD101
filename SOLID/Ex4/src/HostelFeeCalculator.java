import java.util.*;

public class HostelFeeCalculator {

    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
    }

    public void process(BookingRequest req) {

        List<PricingComponent> components = new ArrayList<>();
        components.add(RoomPricing.of(req.roomType));

        for (AddOn addOn : req.addOns) {
            components.add(AddOnPricing.of(addOn));
        }

        Money monthlyTotal = new Money(0);
        Money depositTotal = new Money(0);

        for (PricingComponent component : components) {
            monthlyTotal = monthlyTotal.plus(component.monthly());
            depositTotal = depositTotal.plus(component.deposit());
        }

        ReceiptPrinter.print(req, monthlyTotal, depositTotal);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthlyTotal, depositTotal);
    }
}
