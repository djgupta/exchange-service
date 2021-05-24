package exchange.writer;

import java.util.List;

public interface Writer<T> {

	void write(List<T> orders);
	
	void write(T order);
}
