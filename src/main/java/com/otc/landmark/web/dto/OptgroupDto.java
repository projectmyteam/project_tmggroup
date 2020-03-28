package com.otc.landmark.web.dto;

import java.util.ArrayList;
import java.util.List;

public class OptgroupDto extends Select2Dto{
	
	private List<OptionDto> children = new ArrayList<OptionDto>();

	public List<OptionDto> getChildren() {
		return children;
	}

	public void setChildren(List<OptionDto> children) {
		this.children = children;
	}
	
}
