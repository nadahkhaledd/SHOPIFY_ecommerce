package org.example.typeEditor;

import org.example.entity.Address;
import org.example.entity.Category;
import org.example.service.address.AddressService;
import org.example.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

public class AddressEditor extends PropertyEditorSupport {

    private AddressService addressService;

    @Autowired
    public AddressEditor(AddressService addressService) {
        this.addressService = addressService;
    }

    public AddressEditor() {
    }

    @Override
    public String getAsText(){
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Address address = addressService.getAddressById(Integer.parseInt(text)).getObjectToBeReturned();
        this.setValue(address);
    }
}
