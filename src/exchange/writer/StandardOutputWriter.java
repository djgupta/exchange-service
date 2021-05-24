package exchange.writer;

import java.util.List;

import exchange.dto.Order;

public class StandardOutputWriter implements Writer<Order> {

	@Override
	public void write(List<Order> orders) {
		for(Order order: orders) {
			System.out.println(order.getOrderId()+ " " + order.getMatchedOrderId() + " " + order.getItemType() + " " + order.getPrice() + "/kg " + order.getQuantity()+"kg");
		}
	}

	@Override
	public void write(Order order) {
		System.out.println(order.getOrderId()+ " " + order.getMatchedOrderId() + " " + order.getPrice() + "/kg " + order.getQuantity()+"kg");
	}

}
