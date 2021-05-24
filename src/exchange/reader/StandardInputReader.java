package exchange.reader;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import exchange.dto.Order;

public class StandardInputReader implements Reader<Order> {

	@Override
	public List<Order> read() {
		List<Order> orders = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		
		String line = scanner.nextLine();
		while(!line.equals("")) {
			orders.add(transform(line));
			line = scanner.nextLine();
		}
		scanner.close();
		return orders;
	}
	
	public Order transform(String line) {
		Order order = new Order();
		String[] array = line.split("\\s+");
		order.setOrderId(array[0].stripTrailing().stripLeading());
		order.setTime(LocalTime.parse(array[1].stripTrailing().stripLeading()));
		order.setItemType(array[2].stripTrailing().stripLeading());
		order.setPrice(Integer.parseInt(array[3].stripTrailing().stripLeading().replace("/kg", "")));
		order.setQuantity(Integer.parseInt(array[4].stripTrailing().stripLeading().replace("kg", "")));
		return order;
	}

}
