package exchange.comparator;

import java.util.Comparator;

import exchange.dto.Order;

public class DemandOrderComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		if(o1.getPrice() < o2.getPrice()) {
			return 1;
		}
		else if(o1.getPrice() == o2.getPrice()) {
			return 0;
		}
		else {
			return -1;
		}
	}
	
}
