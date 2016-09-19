
package myshop.order;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import myshop.cart.Cart;
import myshop.cart.CartItem;
import myshop.user.User;
import uutils.PaymentUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class OrderAction extends ActionSupport {
	private Order order;
	private String pd_FrpId;
	// ����ɹ������Ҫ�Ĳ���:
	private String r3_Amt;
	private String r6_Order;
	// 
	private Integer oid;
	
	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	// ע��OrderService
	private OrderService orderService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	// ���涩��ִ�еķ���;
	public String saveOrder(){
		order = new Order();
		/****************��װ����������*********/
		order.setOrdertime(new Date());
		order.setState(1); // 1 δ����   2 �Ѿ�����.  3.�Ѿ�����   4 �Ѿ��ջ�.
		// ��Щ������Ҫ�ӹ��ﳵ�л�ȡ:
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��ù��ﳵ:
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null){
			this.addActionMessage("����û�й���!����ȥ����!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// �����������û�:
		User existUser = (User) request.getSession().getAttribute("existUser");
		if(existUser == null){
			this.addActionMessage("����û�е�¼!����ȥ��¼!");
			return "msg";
		}
		order.setUser(existUser);
		/********************��װ����������*************/
		// ���������ݴ� ����������ݻ��.
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		// ��չ��ﳵ.
		cart.clearCart();
		
		// ���涩��:
		Integer oid = orderService.save(order);
		order.setOid(oid);
		//order = orderService.findByOid(oid);
		//System.out.println(order);
		return "saveOrderSuccess";
	}
	
	
	/**
	 * Ϊ��������ķ���:
	 * @throws IOException 
	 */
	public String payOrder() throws IOException{
		// �޸Ķ���:
		// ��ѯ���id�Ķ���:
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		
		orderService.update(currOrder);
		// ����:
		// ���帶��Ĳ���:
		String p0_Cmd = "Buy";
		String p1_MerId = "10001126856";
		String p2_Order = order.getOid().toString();
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://192.168.40.110:8080/shop/order_callBack.action";
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,pd_FrpId , pr_NeedResponse, keyValue);
		
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		System.out.println(sb.toString());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.sendRedirect(sb.toString());
		return NONE;
	}
	
	
	/**
	 * ����ɹ���Ļص�����
	 */
	public String callBack(){
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(2);// �޸Ķ���״̬.
		orderService.update(currOrder);
		
		this.addActionMessage("��������ɹ�!������:"+r6_Order+" ������:"+r3_Amt);
		return "msg";
	}
	
	/**
	 * ���û�id��ѯ����:
	 */
	public String findByUid(){
		// ����û�����:
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		List<Order> oList = orderService.findByUid(existUser);
		// ѹջ
		ActionContext.getContext().getValueStack().set("oList", oList);
		return "findByUidSuccess";
	}
	
	/**
	 * ��ѯ����:
	 */
	public String findByOid(){
		order = orderService.findByOid(oid);
		return "findByOidSuccess";
	}
}
