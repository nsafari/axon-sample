package commands

import org.axonframework.modelling.command.TargetAggregateIdentifier


class PlaceOrderCommand {
    @TargetAggregateIdentifier
    private val orderId: String? = null
    private val product: String? = null // constructor, getters, equals/hashCode and toString
}

class ConfirmOrderCommand {
    @TargetAggregateIdentifier
    private val orderId: String? = null // constructor, getters, equals/hashCode and toString
}

class ShipOrderCommand {
    @TargetAggregateIdentifier
    private val orderId: String? = null // constructor, getters, equals/hashCode and toString
}