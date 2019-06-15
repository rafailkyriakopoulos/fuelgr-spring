package teilar.cs4414202.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT")
	private Long orderID;
	@ManyToOne
	@JoinColumn(name = "productID", nullable = false)
	private PriceData priceData;
	@ManyToOne
	@JoinColumn(name = "username", nullable = false)
	private User user;
	@Column(columnDefinition = "SMALLINT UNSIGNED")
	@NotNull
	private int quantity;
	@CreationTimestamp
	@Column(name = "\"when\"")
	private LocalDateTime when;

	public Order() {

	}

	public Long getOrderID() {
		return orderID;
	}
	@JsonIgnoreProperties({"gasStation","orders","fuelTypeID","fuelSubTypeID","fuelNormalName",
							"fuelName","fuelPrice","dateUpdated","premium"})
	public PriceData getPriceData() {
		return priceData;
	}
	@JsonIgnoreProperties({"password", "email","gastation","orders" })
	public User getUser() {
		return user;
	}

	public int getQuantity() {
		return quantity;
	}

	public LocalDateTime getWhen() {
		return when;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public void setPriceData(PriceData priceData) {
		this.priceData = priceData;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setWhen(LocalDateTime when) {
		this.when = when;
	}

}