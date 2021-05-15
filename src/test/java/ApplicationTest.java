import commands.PlaceOrderCommand;
import commands.ShipOrderCommand;
import events.OrderConfirmedEvent;
import events.OrderPlacedEvent;
import events.OrderShippedEvent;
import java.util.UUID;
import org.axonframework.messaging.annotation.MessageHandlerInvocationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

public class ApplicationTest {

    private FixtureConfiguration<OrderAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }

    @Test
    public void placeAnOrderShouldPublishOrderPlacedEvent(){

        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.givenNoPriorActivity()
                .when(new PlaceOrderCommand(orderId, product))
                .expectEvents(new OrderPlacedEvent(orderId, product));

    }

    @Test
    public void shipOrderCommandShouldThrowAnExceptionWhenTheOrderIsNotConfirmedYet(){

        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product))
                .when(new ShipOrderCommand(orderId))
                .expectException(MessageHandlerInvocationException.class);

    }

    @Test
    public void placeOrderCommandShouldThrowAnExceptionWhenTheProductIsNotDefinedYet(){

        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product), new OrderConfirmedEvent(orderId))
                .when(new ShipOrderCommand(orderId))
                .expectEvents(new OrderShippedEvent(orderId));

    }
}
