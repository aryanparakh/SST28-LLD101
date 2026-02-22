import java.util.Map;

public class AddOnPricing {

    private static final Map<AddOn, PricingComponent> ADDONS = Map.of(
            AddOn.MESS, new FixedAddOn(1000),
            AddOn.LAUNDRY, new FixedAddOn(500),
            AddOn.GYM, new FixedAddOn(300)
    );

    public static PricingComponent of(AddOn addOn) {
        return ADDONS.get(addOn);
    }

    private static class FixedAddOn implements PricingComponent {
        private final Money monthly;

        FixedAddOn(double monthly) {
            this.monthly = new Money(monthly);
        }

        public Money monthly() { return monthly; }
        public Money deposit() { return new Money(0); }
    }
}
