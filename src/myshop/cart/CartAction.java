package myshop.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import myshop.product.Product;
import myshop.product.ProductService;
import myshop.product.ProductDao;

import com.opensymphony.xwork2.ActionSupport;


public class CartAction extends ActionSupport {
	// ����pid
	private Integer pid;
	// ����count
	private Integer count;
	// ע��ProductService
	private ProductService productService;

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * ��session��Χ��ù��ﳵ�Ĵ���
	 */
	public Cart getCart(HttpServletRequest request) {
		// ��session�ķ�Χ���Cart����.
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// �ж�:
		if (cart == null) {
			// �������ﳵ����
			cart = new Cart();
			// �����ﳵ������뵽session��Χ:
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}

	/**
	 * ��ӵ����ﳵ�ķ���:
	 */
	public String addCart() {
		// ��ѯ��Ʒ��Ϣ:
		Product product = productService.findByPid(pid);
		// ����һ�����������:
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(product);
		// ��ȡ���ﳵ ��Ҫ����request����
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		cart.addCart(cartItem);

		return "addCartSuccess";
	}

	/**
	 * ��չ��ﳵ:
	 */
	public String clearCart() {
		// ��ȡCart����.
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		cart.clearCart();

		return "clearCartSuccess";
	}

	/**
	 * �Ƴ�������
	 */
	public String removeCart() {
		// ��ȡCart����.
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		cart.removeCart(pid);
		return "removeCartSuccess";
	}
	
	/**
	 * �ҵĹ��ﳵ:
	 */
	public String myCart(){
		return "myCartSuccess";
	}
}
