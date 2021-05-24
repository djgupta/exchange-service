package exchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exchange.exchanges.Exchange;
import exchange.reader.Reader;
import exchange.writer.Writer;

public class ExchangeService<T> {
	
	Reader<T> reader;
	Map<String, Exchange<T>> exchanges;
	Writer<T> writer;
	
	public ExchangeService(Reader<T> reader, Map<String, Exchange<T>> exchanges, Writer<T> writer) {
		this.exchanges = exchanges;
		this.reader = reader;
		this.writer = writer;
	}
	
	public Exchange<T> getExchange(String itemType) {
		return exchanges.get(itemType);
	}
	
	public void run(Exchange<T> exchange) {
		List<T> orders = reader.read();
		for(T order: orders) {
			add(exchange, order);
			writer.write(executeAll(exchange));
		}
	}
	
	public T addAndExecute(Exchange<T> exchange, T order) {
		add(exchange, order);
		return execute(exchange);
	}
	
	public boolean add(Exchange<T> exchange, T order) {
		return exchange.addOrder(order);
	}
	
	public boolean addAll(Exchange<T> exchange, List<T> orders) {
		for(T order: orders) {
			add(exchange, order);
		}
		return Boolean.TRUE;
	}
	
	public List<T> executeAll(Exchange<T> exchange) {
		List<T> orders = new ArrayList<>();
		T order = execute(exchange);
		while(order != null) {
			orders.add(order);
			order = execute(exchange);
		}
		return orders;
	}
	
	public T execute(Exchange<T> exchange) {
		return  exchange.execute();
	}
}
