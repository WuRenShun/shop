package cn.test.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.shop.mapping.ProductMapper;
import cn.test.shop.model.Product;
import cn.test.shop.service.ProductService;
import cn.test.shop.utils.PageBean;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<Product> findHot() throws Exception {
		return productMapper.findHot();
	}

	@Override
	public List<Product> findNew() throws Exception {
		// TODO Auto-generated method stub
		return productMapper.findNew();
	}

	@Override
	public Product findByPid(Integer pid) throws Exception {
		return productMapper.selectByPrimaryKey(pid);
	}

	@Override
	public PageBean<Product> findByPageCid(Integer cid, Integer page) 
			throws Exception {
		PageBean<Product> prBean=new PageBean<Product>();
		
		prBean.setPage(page);
		
		int limit=8;
		prBean.setLimit(limit); 
		
		int totalCount=0;
		totalCount=productMapper.findCountCid(cid);
		prBean.setTotalCount(totalCount);
		
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else{
			totalPage=totalCount/limit+1;
		}
		prBean.setTotalPage(totalPage);
		
		int begin=(page-1)*limit;
		List<Product> list=productMapper.findByPageCid(cid,begin,limit);
		prBean.setList(list);
		
		return prBean;
	}

	@Override
	public PageBean<Product> findByPageCsid(Integer csid, Integer page) throws Exception {
		PageBean<Product> prBean=new PageBean<Product>();
		
		prBean.setPage(page);
		
		int limit=8;
		prBean.setLimit(limit); 
		
		int totalCount=0;
		totalCount=productMapper.findCountCsid(csid);
		prBean.setTotalCount(totalCount);
		
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else{
			totalPage=totalCount/limit+1;
		}
		prBean.setTotalPage(totalPage);
		
		int begin=(page-1)*limit;
		List<Product> list=productMapper.findByPageCsid(csid,begin,limit);
		prBean.setList(list);
		
		return prBean;
	}

	@Override
	public PageBean<Product> findByPage(Integer page) throws Exception {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = productMapper.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productMapper.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(Product product) throws Exception {
		productMapper.insertSelective(product);
	}

	@Override
	public void update(Product product) throws Exception {
		productMapper.updateByPrimaryKeySelective(product);
	}

	@Override
	public void delete(Integer pid) throws Exception {
		productMapper.deleteByPrimaryKey(pid);
	}
	
	
}
