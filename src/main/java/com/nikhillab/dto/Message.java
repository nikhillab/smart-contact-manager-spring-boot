package com.nikhillab.dto;

public class Message {

	public String content;
	public MessageType type;

	public Message(String content, MessageType type) {
		this.content = content;
		this.type = type;
	}

	public static enum MessageType {
		blue, green, yellow, red
	}

}
