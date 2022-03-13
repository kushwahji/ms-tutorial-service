package com.santosh.javatutorials.response;

import com.santosh.javatutorials.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponseDto {
	private Long menuId;
	private String name;
	private boolean status;

	public MenuResponseDto(Menu menu) {
		this.menuId = menu.getMenuId();
		this.name = menu.getName();
		this.status = menu.isStatus();
	}
}
