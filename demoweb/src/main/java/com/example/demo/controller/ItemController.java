package com.example.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.validator.ItemValidator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Controller
public class ItemController {
	private final Logger logger = LoggerFactory.getLogger(ItemController.class);
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	ItemValidator itemValidator;
	//Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(itemValidator);
		}
	
	public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
	@GetMapping("/listitems")
	public String showAllItems(Model model) {
		List<Item> item = itemRepository.findAll();
		model.addAttribute("item", item);
		return "itemlist";
    }
	@GetMapping("/aggregateitem")
	public String aggregateitem(Model model) {
		logger.debug("aggregateitem");
		Item item = new Item();
		item.setId(00000);
		item.setName("juanito");
		item.setQuantity(5);
		model.addAttribute("itemForm", item);
        return "listform";
    }

	@GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable(value = "id")  Long ItemId)
        throws ResourceNotFoundException {
        Item item = itemRepository.findById(ItemId)
          .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + ItemId));
        return ResponseEntity.ok().body(item);
    }
	 
    @PostMapping("/aggregateitem")
    public String saveOrUpdate(@ModelAttribute("") @Validated Item item,
    		BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
    	logger.debug("saveOrUpdateItem() : {}", item);

		if (result.hasErrors()) {
			return "users/userform";
		} else {

			// Add message to flash scope
			redirectAttributes.addFlashAttribute("msg", "success");	
			itemRepository.save(item);
			
			// POST/REDIRECT/GET
			return "redirect:/itemlist";        
    }
    }
    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable(value = "id") Long ItemId,
         @Valid @RequestBody Item ItemDetails) throws ResourceNotFoundException {
        Item item = itemRepository.findById(ItemId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + ItemId));
        item.setName(ItemDetails.getName());
        item.setQuantity(ItemDetails.getQuantity());
        final Item updatedItem = itemRepository.save(item);
        return ResponseEntity.ok(updatedItem);
    }
    
    @DeleteMapping("/items/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long ItemId)
         throws ResourceNotFoundException {
    	Item item = itemRepository.findById(ItemId)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + ItemId));

    	itemRepository.delete(item);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
    
	
	
	

}
