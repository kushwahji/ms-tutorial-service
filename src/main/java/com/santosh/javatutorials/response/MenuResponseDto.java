package com.santosh.javatutorials.response;

import com.santosh.javatutorials.entity.Menu;
import com.santosh.javatutorials.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponseDto {
    private Long menuId;
    private String name;

    public MenuResponseDto(Menu menu) {
        this.menuId = menu.getMenuId();
        this.name=menu.getName();
        }
}
