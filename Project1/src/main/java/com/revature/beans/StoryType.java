package com.revature.beans;

public enum StoryType {
	ARTICLE(10), SHORT_STORY(20), NOVELLA(25), NOVEL(50);
	
	private final int points;
	
	StoryType(int points) {
		this.points = points;
	}
	
	public int getPoints() {
		return points;
	}
}
