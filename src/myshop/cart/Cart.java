package myshop.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	private Map<Integer,CartItem> map = new HashMap<Integer,CartItem>();
	
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	
	private Double total = 0d;
	
	public Double getTotal() {
		return total;
	}

	
	public void addCart(CartItem cartItem){
		
		Integer pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			
			map.put(pid, cartItem);
		}
		
		total += cartItem.getSubtotal();
	}
	
	
	public void removeCart(Integer pid){
		
		CartItem cartItem = map.remove(pid);
		
		total -= cartItem.getSubtotal();
	}
	
	// 清空购物车:
	public void clearCart(){
		// 清空map
		map.clear();
		// 总计设置为0
		total = 0d;
	}
}

