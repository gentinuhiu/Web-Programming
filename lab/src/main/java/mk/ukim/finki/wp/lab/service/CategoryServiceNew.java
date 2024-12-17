package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exception.LocationNotFoundException;
import mk.ukim.finki.wp.lab.repository.ICategoryRepository;
import mk.ukim.finki.wp.lab.repository.ILocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceNew {
    private final ICategoryRepository categoryRepository;

    public CategoryServiceNew(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
    }
}