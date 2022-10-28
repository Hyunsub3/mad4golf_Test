package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {
	private String board_num;
	private String board_category;
	private String user_id;
	private String user_name;
	
	private String title;
	private String content;
	private String content_img;
	private String content_real_img;
	private String content_file;
	private String content_real_file;
	private Timestamp write_date;
	private int re_reference;
	private int re_sequence;
	private int readcount; 

	
}
