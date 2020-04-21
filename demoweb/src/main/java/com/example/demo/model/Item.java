package com.example.demo.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "item")
public class Item {
	private long id;
	private String name;
	private int quantity;
	public Item(){	
		
	}
	public Item(String name,int quantity){
		this.quantity = quantity;
		this.name = name;		
	}
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 public long getId() {
		 return id;}
	 
	 public void setId(long id) {
		 this.id = id;
	    }
	 @Column(name = "name", nullable = false)
	 public String getName() {
		 return name;
	    }
	 public void setName(String name) {
		 this.name = name;
	    }
	 @Column(name = "quantity", nullable = false)
	 public int getQuantity() {
		 return quantity;
	    }
	 public void setQuantity(int quantity) {
		 this.quantity = quantity;
	    }
	 @Override
	    public String toString() {
		 return "Employee [id=" + id + ", Name=" + name +  ", Quantity=" + quantity + "]";
	    }
}
