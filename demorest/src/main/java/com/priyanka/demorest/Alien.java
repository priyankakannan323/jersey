package com.priyanka.demorest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Alien {
	private String name;
	private int points;
	private String alienId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getId() {
		return alienId;
	}
	public void setId(String alienId) {
		this.alienId = alienId;
	}

}
