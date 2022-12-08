package org.example.model;

public class Email {

    private String receiver;
    private String sender;
    private String subject;
    private String body;

    public Email() {
    }

    public Email(String receiver, String sender, String subject, String body) {
        this.receiver = receiver;
        this.sender = sender;
        this.subject = subject;
        this.body = body;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
