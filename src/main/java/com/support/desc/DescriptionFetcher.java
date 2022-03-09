package com.support.desc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.support.repository.CategoryRepository;
import com.support.repository.PrioriryRepository;
import com.support.repository.StatusRepository;
import com.support.repository.SubCategoryRepository;

@Service
public class DescriptionFetcher {

	private final CategoryRepository categoryRepo;

	private final SubCategoryRepository subCategoryRepo;

	private final StatusRepository statusRepo;

	private final PrioriryRepository prioriryRepo;

	@Autowired
	public DescriptionFetcher(CategoryRepository categoryRepo, SubCategoryRepository subCategoryRepo,
			StatusRepository statusRepo, PrioriryRepository prioriryRepo) {
		super();
		this.categoryRepo = categoryRepo;
		this.subCategoryRepo = subCategoryRepo;
		this.statusRepo = statusRepo;
		this.prioriryRepo = prioriryRepo;
	}

	public String getCategoryDesById(int id) {
		return categoryRepo.findById((long) id).get().getCategory_desc();
	}

	public String getSubCatagoryDescById(int id) {
		return subCategoryRepo.findById((long) id).get().getSub_category_desc();
	}

	public String getSatusDescById(int id) {
		return statusRepo.findById((long) id).get().getStatus_desc();
	}

	public String getPriorityDescById(int id) {
		return prioriryRepo.findById((long) id).get().getPriority_desc();
	}


}
