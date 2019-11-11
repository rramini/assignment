package com.assignment.publisher.models;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * PublisherResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-11T08:52:16.551Z")

public class PublisherResponse {
	@JsonProperty("statusCode")
	private String statusCode = null;

	@JsonProperty("statusDescription")
	private String statusDescription = null;

	public PublisherResponse statusCode(String statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	/**
	 * status code
	 * 
	 * @return statusCode
	 **/
	@ApiModelProperty(value = "status code")

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public PublisherResponse statusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
		return this;
	}

	/**
	 * status description
	 * 
	 * @return statusDescription
	 **/
	@ApiModelProperty(value = "status description")

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PublisherResponse publisherResponse = (PublisherResponse) o;
		return Objects.equals(this.statusCode, publisherResponse.statusCode)
				&& Objects.equals(this.statusDescription, publisherResponse.statusDescription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(statusCode, statusDescription);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PublisherResponse {\n");

		sb.append("    statusCode: ").append(toIndentedString(statusCode)).append("\n");
		sb.append("    statusDescription: ").append(toIndentedString(statusDescription)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
