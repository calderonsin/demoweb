package com.example.demo.validator;
import com.example.demo.model.Item;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Item.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Item item = (Item) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemname", "NotEmpty");
        if (item.getName().length() < 1 || item.getName().length() > 32) {
            errors.rejectValue("itemname", "Size.itemForm.itemname");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemquantity", "NotEmpty");
        if (item.getQuantity() < 1) {
            errors.rejectValue("itemquantity", "Size.itemForm.itemquantity");
        }

        
    }
}