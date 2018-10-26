package com.eim;

import com.eim.dao.ProductCategoryDao;
import com.eim.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellApplicationTests {

	@Autowired
	private ProductCategoryDao pdao;

	@Test
	public void contextLoads() {
		ProductCategory one = pdao.findOne(1);
		System.out.println(one);
	}
	@Test
	public void add(){
		ProductCategory p = new ProductCategory();
		p.setCategoryName("德玛");
		p.setCategoryType(78);
		ProductCategory save = pdao.save(p);
		Assert.assertNotNull(save);
	}
	@Test
	public void update(){
		ProductCategory one = pdao.findOne(9);
		one.setCategoryName("爱哭的小姑凉");
		pdao.save(one);
	}
	@Test
	public void findByCategory_typeIn(){
		List<Integer> list = Arrays.asList(1,4,89);
		List<ProductCategory> byCategory_typeIn = pdao.findBycategoryTypeIn(list);
		System.out.println(byCategory_typeIn);
		Assert.assertNotEquals(0,byCategory_typeIn.size());
	}




}
