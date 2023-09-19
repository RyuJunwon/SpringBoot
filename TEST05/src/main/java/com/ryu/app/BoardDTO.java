package com.ryu.app;

import lombok.Data;

@Data
public class BoardDTO {
	private int bid; // PK
	private String mid; // FK
	private String content;
}
