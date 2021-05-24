package exchange.dto;

import java.time.LocalTime;

public class Order {
	
	String orderId;
	LocalTime time;
    String itemType;
    int price;
    int quantity;
    String matchedOrderId;
    
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getMatchedOrderId() {
		return matchedOrderId;
	}
	public void setMatchedOrderId(String matchedOrderId) {
		this.matchedOrderId = matchedOrderId;
	}
}
