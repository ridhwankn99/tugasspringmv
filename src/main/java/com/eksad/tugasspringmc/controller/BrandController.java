package com.eksad.tugasspringmc.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eksad.tugasspringmc.model.Brand;
import com.eksad.tugasspringmc.repository.BrandRepositoryDao;

@Controller
@RequestMapping("/brand")
public class BrandController {
	@Autowired
	BrandRepositoryDao brandRepositoryDao;
	
	@RequestMapping("")
	public String index (Model model) {
		List<Brand> list = brandRepositoryDao.findAll();
		
		model.addAttribute("listBrand", list);
		return "list";
		
		
	}
	
	@RequestMapping("/add")
	public String addBrand(Model model) {
		Brand brand = new Brand();
		
		model.addAttribute("brand", brand);
		return "add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute Brand brand) {
		brandRepositoryDao.save(brand);
		
		return "redirect:/brand";
	}
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable ("id") Long id) {
		Brand brand = brandRepositoryDao.getOne(id);
		if(brand==null)
		{
			throw new EntityNotFoundException("Not Found");
		}
		brandRepositoryDao.delete(brand);
		return "redirect:/brand";
	}	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBrand(@PathVariable Long id, Model model) {
		Brand brand = brandRepositoryDao.findOne(id);
		model.addAttribute("brand", brand);
		
		return "add";
	}
}
