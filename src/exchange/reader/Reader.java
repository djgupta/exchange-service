package exchange.reader;

import java.util.List;

public interface Reader<T> {

	List<T> read();
}
