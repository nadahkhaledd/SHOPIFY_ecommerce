package org.example.typeEditor;

import org.example.entity.Category;
import org.example.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class CategoryTypeEditor extends PropertyEditorSupport {


    private CategoryService categoryService;


    @Autowired
    public CategoryTypeEditor(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CategoryTypeEditor() {
    }

    @Override
    public String getAsText(){
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Category category = categoryService.getCategoryByName(text).getObjectToBeReturned();
        this.setValue(category);
    }
}
