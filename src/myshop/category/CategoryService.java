package myshop.category;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CategoryService {
private CategoryDao categorydao;








public void setCategorydao(CategoryDao categorydao) {
	this.categorydao = categorydao;
}








public List<Category> findALL() {
	// TODO �Զ����ɵķ������
	return categorydao.findALL();
}
}
