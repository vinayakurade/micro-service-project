package com.user.service.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hotel {
	
	private Long id;
	private String name;
	private String location;
	private String about;
}
