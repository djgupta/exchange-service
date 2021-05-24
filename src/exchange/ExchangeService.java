package exchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import exchange.exchanges.Exchange;
import exchange.exchanges.MainExchange;
import exchange.reader.Reader;
import exchange.writer.Writer;

public class ExchangeService<T> {
	
	Reader<T> reader;
	Map<String, Exchange<T>> exchanges;
	Writer<T> writer;
	Function<T, String> getter;
	
	public ExchangeService(Reader<T> reader, Map<String, Exchange<T>> exchanges, Writer<T> writer, Function<T, String> getter) {
		this.exchanges = exchanges;
		this.reader = reader;
		this.writer = writer;
		this.getter = getter;
	}
	
	public Exchange<T> getExchange(String itemType) {
		return exchanges.getOrDefault(itemType, (Exchange<T>) new MainExchange());
	}
	
	public Exchange<T> setExchange(String itemType, Exchange<T> exchange) {
		return exchanges.put(itemType, exchange);
	}
	
	public void run() {
		List<T> orders = reader.read();
		for(T order: orders) {
			Exchange<T> exchange = getExchange(getter.apply(order));
			add(exchange, order);
			writer.write(executeAll(exchange));
			setExchange(getter.apply(order), exchange);
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
