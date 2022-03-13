package com.santosh.javatutorials.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santosh.javatutorials.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

	@JsonIgnore
	private Long menuId;
	
	@NotBlank(message = "name should not be blank or null")
	private String name;

	public MenuDto(Menu menu) {
		this.menuId = menu.getMenuId();
		this.name = menu.getName();
	}

}
