package com.revature.beans;

import java.sql.Date;

public class PitchFeedback {
	private Integer id;
	private Pitch pitch;
	private Person editor; //person who is giving feedback; role must be that of an editor,
	//with authority of greater scope than the editor who most recently approved this pitch, if any
	private Status status; //the status being conferred to this pitch by an editor
	//this must be null unless editor is a senior editor
	private String explanation;
	private String newTagLine; 
	private String newTentativeTitle;
	private Date newTentativeCompletionDate;
}
