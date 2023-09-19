package com.ryu.app;

import lombok.Data;

@Data
public class MemberDTO {
	private String mid; // PK
	private String mpw;
	
	private String SearchCondition;
}
