package com.assignment.publisher.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * PublisherRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-11T08:52:16.551Z")

public class PublisherRequest {
	@JsonProperty("customerNumber")
	private Integer customerNumber = null;

	@JsonProperty("firstName")
	private String firstName = null;

	@JsonProperty("lastName")
	private String lastName = null;

	@JsonProperty("birthdate")
	private LocalDate birthdate = null;

	@JsonProperty("country")
	private String country = null;

	@JsonProperty("countryCode")
	private String countryCode = null;

	@JsonProperty("mobileNumber")
	private BigDecimal mobileNumber = null;

	@JsonProperty("email")
	private String email = null;

	/**
	 * Gets or Sets customerStatus
	 */
	public enum CustomerStatusEnum {
		RESTORED("Restored"),

		SUSPENDED("Suspended"),

		OPEN("Open"),

		CLOSED("Closed");

		private String value;

		CustomerStatusEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static CustomerStatusEnum fromValue(String text) {
			for (CustomerStatusEnum b : CustomerStatusEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("customerStatus")
	private CustomerStatusEnum customerStatus = null;

	@JsonProperty("address")
	private Address address = null;

	public PublisherRequest customerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
		return this;
	}

	/**
	 * customer number,alphanumeric and min 5 and max 50 length
	 * 
	 * @return customerNumber
	 **/
	@ApiModelProperty(required = true, value = "customer number,alphanumeric and min 5 and max 50 length")
	@NotNull

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public PublisherRequest firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	/**
	 * Get firstName
	 * 
	 * @return firstName
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Size(min = 10, max = 50)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public PublisherRequest lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * Get lastName
	 * 
	 * @return lastName
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Size(min = 10, max = 50)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public PublisherRequest birthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
		return this;
	}

	/**
	 * date format
	 * 
	 * @return birthdate
	 **/
	@ApiModelProperty(example = "DD-MM-YYYY", required = true, value = "date format")
	@NotNull

	@Valid

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public PublisherRequest country(String country) {
		this.country = country;
		return this;
	}

	/**
	 * Get country
	 * 
	 * @return country
	 **/
	@ApiModelProperty(example = "India", required = true, value = "")
	@NotNull

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public PublisherRequest countryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
	}

	/**
	 * Get countryCode
	 * 
	 * @return countryCode
	 **/
	@ApiModelProperty(example = "IN", required = true, value = "")
	@NotNull

	@Size(max = 2)
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public PublisherRequest mobileNumber(BigDecimal mobileNumber) {
		this.mobileNumber = mobileNumber;
		return this;
	}

	/**
	 * Get mobileNumber
	 * 
	 * @return mobileNumber
	 **/
	@ApiModelProperty(example = "5.555551216E9", required = true, value = "")
	@NotNull

	@Valid

	public BigDecimal getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(BigDecimal mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public PublisherRequest email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * Get email
	 * 
	 * @return email
	 **/
	@ApiModelProperty(example = "abc@gmail.com", required = true, value = "")
	@NotNull

	@Size(max = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PublisherRequest customerStatus(CustomerStatusEnum customerStatus) {
		this.customerStatus = customerStatus;
		return this;
	}

	/**
	 * Get customerStatus
	 * 
	 * @return customerStatus
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public CustomerStatusEnum getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(CustomerStatusEnum customerStatus) {
		this.customerStatus = customerStatus;
	}

	public PublisherRequest address(Address address) {
		this.address = address;
		return this;
	}

	/**
	 * Get address
	 * 
	 * @return address
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PublisherRequest publisherRequest = (PublisherRequest) o;
		return Objects.equals(this.customerNumber, publisherRequest.customerNumber)
				&& Objects.equals(this.firstName, publisherRequest.firstName)
				&& Objects.equals(this.lastName, publisherRequest.lastName)
				&& Objects.equals(this.birthdate, publisherRequest.birthdate)
				&& Objects.equals(this.country, publisherRequest.country)
				&& Objects.equals(this.countryCode, publisherRequest.countryCode)
				&& Objects.equals(this.mobileNumber, publisherRequest.mobileNumber)
				&& Objects.equals(this.email, publisherRequest.email)
				&& Objects.equals(this.customerStatus, publisherRequest.customerStatus)
				&& Objects.equals(this.address, publisherRequest.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerNumber, firstName, lastName, birthdate, country, countryCode, mobileNumber, email,
				customerStatus, address);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PublisherRequest {\n");

		sb.append("    customerNumber: ").append(toIndentedString(customerNumber)).append("\n");
		sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
		sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
		sb.append("    birthdate: ").append(toIndentedString(birthdate)).append("\n");
		sb.append("    country: ").append(toIndentedString(country)).append("\n");
		sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
		sb.append("    mobileNumber: ").append(toIndentedString(mobileNumber)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("    customerStatus: ").append(toIndentedString(customerStatus)).append("\n");
		sb.append("    address: ").append(toIndentedString(address)).append("\n");
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
