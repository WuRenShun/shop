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
	
	
}
