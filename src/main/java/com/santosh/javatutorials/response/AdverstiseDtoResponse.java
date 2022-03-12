/**
 * 
 */
package com.santosh.javatutorials.response;

import com.santosh.javatutorials.entity.Adverstise;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Data
@NoArgsConstructor
public class AdverstiseDtoResponse {
	private Long id;
	private String path;
	
	public AdverstiseDtoResponse(Adverstise m) {
		this.path = m.getLink();
		this.id = m.getAdsId();
	}
}
