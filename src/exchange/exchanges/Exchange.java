package exchange.exchanges;

public interface Exchange<T> {

	boolean addOrder(T order);
	
	T execute();
}
