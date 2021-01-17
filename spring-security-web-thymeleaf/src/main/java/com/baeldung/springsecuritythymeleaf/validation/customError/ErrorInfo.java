package com.baeldung.springsecuritythymeleaf.validation.customError;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name= "ErrorInfo")
public class ErrorInfo {

    private String url;
    @ApiModelProperty(notes = "HTTP Status Code")
    private int statusCode;

    @ApiModelProperty(notes = "HTTP Reason Phrase")
    private String reasonPhrase;

    @ApiModelProperty(notes = "Mensage to the user")
    private String message;

    @ApiModelProperty(notes = "Ticket created on IT help desk if applicable", required = false)
    private String helpDeskTicket;

    @ApiModelProperty(notes = "Debug information (e.g., stack trace), not visible if runtime environment is 'production'", required = false)
    private String debugInfo;

    public ErrorInfo() {
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
