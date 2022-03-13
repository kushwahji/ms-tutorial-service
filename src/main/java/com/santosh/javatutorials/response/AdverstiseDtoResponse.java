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
	private Long adsId;
	private boolean status;
	private String link;
	
	public AdverstiseDtoResponse(Adverstise m) {
		this.link = m.getLink();
		this.status = m.isStatus();
		this.adsId = m.getAdsId();
	}
}
