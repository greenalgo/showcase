package com.green.base.utility;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GreenUtility {

	public <T> T getTopResult(List<T> result) {
		return result == null ? null : result.get(0);
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getNullSafeList(List<T> result) {
		return result == null ? Collections.EMPTY_LIST : result;
	}	

}
