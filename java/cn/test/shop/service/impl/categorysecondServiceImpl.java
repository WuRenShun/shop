package cn.test.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.shop.mapping.CategorysecondMapper;
import cn.test.shop.model.Categorysecond;
import cn.test.shop.service.CategorysecondService;
import cn.test.shop.utils.PageBean;

@Service("CategorysecondService")
public class CategorysecondServiceImpl implements CategorysecondService {
	
	@Autowired
	private CategorysecondMapper categorysecondMapper;

	@Override
	public PageBean<Categorysecond> findByPage(Integer page) throws Exception {
		PageBean<Categorysecond> pageBean=new PageBean<Categorysecond>();
		
		int limit=10;
		pageBean.setLimit(limit);
		
		pageBean.setPage(page);
		
		int totalCount=categorysecondMapper.findCount();
		pageBean.setTotalCount(totalCount);
		
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount / limit;
		}else{
			totalPage=totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		int begin=(page - 1) * limit;
		List<Categorysecond> list=categorysecondMapper.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(Categorysecond categorysecond) throws Exception {
		categorysecondMapper.insertSelective(categorysecond);
	}

	@Override
	public Categorysecond findByCsid(Integer csid) throws Exception {
		return categorysecondMapper.selectByPrimaryKey(csid);
	}

	@Override
	public void update(Categorysecond categorysecond) throws Exception {
		categorysecondMapper.updateByPrimaryKeySelective(categorysecond);
	}

	@Override
	public void delete(Integer csid) throws Exception {
		categorysecondMapper.deleteByPrimaryKey(csid);
	}

	
	@Override
	public void deleteCid(Integer cid) throws Exception {
		categorysecondMapper.deleteCid(cid);
	}

	@Override
	public List<Categorysecond> findAll() throws Exception {
		return categorysecondMapper.findAll();
	}

	

	
	
	
	

}
