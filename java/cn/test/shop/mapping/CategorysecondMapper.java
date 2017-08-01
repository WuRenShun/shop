package cn.test.shop.mapping;

import java.util.List;

import cn.test.shop.model.Categorysecond;

public interface CategorysecondMapper {
    int deleteByPrimaryKey(Integer csid);

    int insert(Categorysecond record);

    int insertSelective(Categorysecond record);

    Categorysecond selectByPrimaryKey(Integer csid);

    int updateByPrimaryKeySelective(Categorysecond record);

    int updateByPrimaryKey(Categorysecond record);

	int findCount();

	List<Categorysecond> findByPage(int begin, Integer page);

	void deleteCid(Integer cid);

	List<Categorysecond> findAll();
}