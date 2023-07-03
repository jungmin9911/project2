package com.example.world.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AttractionVO {
		private Integer aseq;
		private Integer pnum;
		@NotEmpty(message="어트랙션 이름을 입력하세요")
		private String atname;
		@NotEmpty(message="어트랙션 설명을 입력하세요")
		private String acontent;
		private String act1;
		private String act2;
		@NotEmpty(message="어트랙션 이미지를 입력하세요")
		private String image;
		@NotEmpty(message="어트랙션 키제한을 입력하세요")
		private String limitkey;
		@NotEmpty(message="어트랙션 나이제한사항을 입력하세요")
		private String limitage;
		private String bestat;
		private String aresult;
		
		
		
	
		
		
		
		
	
		
	
}
