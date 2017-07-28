package cn.test.shop.model;

import java.util.List;

public class Category {
    private Integer cid;

    private String cname;
    
    private CategoryCustom categoryCustom;
	
	//一对多关系，一级分类对应多个二级分类
	private List<Categorysecond> categoryseconds;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

	public CategoryCustom getCategoryCustom() {
		return categoryCustom;
	}

	public void setCategoryCustom(CategoryCustom categoryCustom) {
		this.categoryCustom = categoryCustom;
	}

	public List<Categorysecond> getCategoryseconds() {
		return categoryseconds;
	}

	public void setCategoryseconds(List<Categorysecond> categoryseconds) {
		this.categoryseconds = categoryseconds;
	}
    
    
}