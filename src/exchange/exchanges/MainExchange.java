package exchange.exchanges;

import java.util.Comparator;
import java.util.PriorityQueue;

import exchange.dto.Order;

public class MainExchange implements Exchange<Order>{
	
	PriorityQueue<Order> demandQ = new PriorityQueue<>(Comparator.comparing(Order::getPrice).reversed().thenComparing(Order::getTime));
	PriorityQueue<Order> supplyQ = new PriorityQueue<>(Comparator.comparing(Order::getPrice).thenComparing(Order::getTime));

	@Override
	public boolean addOrder(Order order) {
		if(order.getOrderId().charAt(0) == 'd') {
			return demandQ.add(order);
		}
		else {
			return supplyQ.add(order);
		}
	}
	
	@Override
	public Order execute() {
		Order executedOrder = null;
		Order supplyOrder = supplyQ.peek();
		Order demandOrder = demandQ.peek();
		if( supplyOrder != null && demandOrder != null && supplyOrder.getPrice() <= demandOrder.getPrice()) {
			supplyOrder = supplyQ.poll();
			demandOrder = demandQ.poll();
			if(supplyOrder.getQuantity() > demandOrder.getQuantity()) {
				demandQ.remove(demandOrder);
				supplyOrder.setQuantity(supplyOrder.getQuantity() - demandOrder.getQuantity());
				supplyQ.add(supplyOrder);
				
				executedOrder = demandOrder;
				executedOrder.setMatchedOrderId(supplyOrder.getOrderId());
				executedOrder.setPrice(supplyOrder.getPrice());
			}
			else if(supplyOrder.getQuantity() == demandOrder.getQuantity()) {
				supplyQ.remove(supplyOrder);
				demandQ.remove(demandOrder);
				
				executedOrder = supplyOrder;
				executedOrder.setMatchedOrderId(supplyOrder.getOrderId());
				executedOrder.setOrderId(demandOrder.getOrderId());
			}
			else {
				supplyQ.remove(supplyOrder);
				demandOrder.setQuantity(demandOrder.getQuantity() - supplyOrder.getQuantity());
				demandQ.add(demandOrder);
				
				executedOrder = supplyOrder;
				executedOrder.setMatchedOrderId(supplyOrder.getOrderId());
				executedOrder.setOrderId(demandOrder.getOrderId());
			}
		}
		return executedOrder;
	}
}
