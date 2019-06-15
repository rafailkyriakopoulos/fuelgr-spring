package teilar.cs4414202.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="pricedata")
public class PriceData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT UNSIGNED")
	private Long productID;
	
	@ManyToOne
	@JoinColumn(name="gasStationID",nullable=false)
	@JsonIgnoreProperties({"gasStationLat","gasStationLong","fuelCompID","fuelCompNormalName","gasStationOwner",
		"ddNormalName","municipalityID","municipalityNormalName","countyID","countyName","gasStationAddress","phone1","user","priceData"})
	private GasStation gasStation;
	
	@OneToMany(mappedBy="priceData",fetch=FetchType.LAZY)
	private List<Order> orders;
	
	@NotNull
	@Column(columnDefinition="tinyint UNSIGNED")
	private short fuelTypeID;
	
	@NotNull
	@Column(columnDefinition="tinyint UNSIGNED")
	private short fuelSubTypeID;
	
	@NotNull
	@Column(length=64)
    private String fuelNormalName;
	
	@NotNull
	@Column(length=128)
    private String fuelName;
	
	@NotNull
	@Column(precision=4, scale=3)
	private BigDecimal fuelPrice;
	
	@UpdateTimestamp
	private LocalDateTime dateUpdated;
	
	@Column(columnDefinition="BOOLEAN", nullable=true)
	private Boolean isPremium;
	
	
	
	
	public PriceData() {
		super();
	}

	public Long getProductID() {
		return productID;
	}
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="gasStationID")
	@JsonIdentityReference(alwaysAsId=true)
	public GasStation getGasStation() {
		return gasStation;
	}

	public short getFuelTypeID() {
		return fuelTypeID;
	}

	public String getFuelNormalName() {
		return fuelNormalName;
	}

	public String getFuelName() {
		return fuelName;
	}

	public BigDecimal getFuelPrice() {
		return fuelPrice;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public boolean isPremium() {
		return isPremium;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public void setGasStationID(GasStation gasStationID) {
		this.gasStation = gasStationID;
	}

	public void setFuelTypeID(short fuelTypeID) {
		this.fuelTypeID = fuelTypeID;
	}

	public void setFuelNormalName(String fuelNormalName) {
		this.fuelNormalName = fuelNormalName;
	}

	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}

	public void setFuelPrice(BigDecimal fuelPrice) {
		this.fuelPrice = fuelPrice;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

	public short getFuelSubTypeID() {
		return fuelSubTypeID;
	}

	public void setFuelSubTypeID(short fuelSubTypeID) {
		this.fuelSubTypeID = fuelSubTypeID;
	}

	@JsonIgnore
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	

}