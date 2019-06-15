package teilar.cs4414202.model;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="gasstations")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GasStation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "SMALLINT UNSIGNED")
	private Long gasStationID;
	@Column(precision=10, scale=7)
	private BigDecimal gasStationLat;
	@Column(precision=10, scale=7)
	private BigDecimal gasStationLong;
	@NotNull
	@Column(columnDefinition="tinyint")
	private short fuelCompID;
	@Column(length = 45)
	@NotNull
	private String fuelCompNormalName;
	@Column(length = 128)
	@NotNull
	private String gasStationOwner;
	@Column(length = 10)
	@NotNull
	private String ddID;
	@Column(length = 45)
	@NotNull
	private String ddNormalName;
	@Column(length = 10)
	@NotNull
	private String municipalityID;
	@Column(length = 45)
	@NotNull
	private String municipalityNormalName;
	@Column(length = 10)
	@NotNull
	private String countyID;
	@Column(length = 64)
	@NotNull
	private String countyName;
	@Column(length = 255)
	private String gasStationAddress;
	
	@Column(columnDefinition="CHAR(10)")
	private String phone1;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="username",nullable=false)
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="username")
	@JsonIdentityReference(alwaysAsId=true)
	private User user;
	@OneToMany(mappedBy="gasStation", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<PriceData> priceData;
	
	
	public GasStation() {
		
	}
	
	
	public List<PriceData> getPriceData() {
		return priceData;
	}


	public void setPriceData(List<PriceData> pricesData) {
		this.priceData = pricesData;
	}


	public Long getGasStationID() {
		return gasStationID;
	}
	public BigDecimal getGasStationLat() {
		return gasStationLat;
	}
	public BigDecimal getGasStationLong() {
		return gasStationLong;
	}
	public short getFuelCompID() {
		return fuelCompID;
	}
	public String getFuelCompNormalName() {
		return fuelCompNormalName;
	}
	public String getGasStationOwner() {
		return gasStationOwner;
	}
	public String getDdID() {
		return ddID;
	}
	public String getDdNormalName() {
		return ddNormalName;
	}
	public String getMunicipalityID() {
		return municipalityID;
	}
	public String getMunicipalityNormalName() {
		return municipalityNormalName;
	}
	public String getCountyID() {
		return countyID;
	}
	public String getCountyName() {
		return countyName;
	}
	public String getGasStationAddress() {
		return gasStationAddress;
	}
	public String getPhone1() {
		return phone1;
	}
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="username")
	@JsonIdentityReference(alwaysAsId=true)
	public User getUser() {
		return user;
	}
	public void setGasStationID(Long gasStationID) {
		this.gasStationID = gasStationID;
	}
	public void setGasStationLat(BigDecimal gasStationLat) {
		this.gasStationLat = gasStationLat;
	}
	public void setGasStationLong(BigDecimal gasStationLong) {
		this.gasStationLong = gasStationLong;
	}
	public void setFuelCompID(short fuelCompID) {
		this.fuelCompID = fuelCompID;
	}
	public void setFuelCompNormalName(String fuelCompNormalName) {
		this.fuelCompNormalName = fuelCompNormalName;
	}
	public void setGasStationOwner(String gasStationOwner) {
		this.gasStationOwner = gasStationOwner;
	}
	public void setDdID(String ddID) {
		this.ddID = ddID;
	}
	public void setDdNormalName(String ddNormalName) {
		this.ddNormalName = ddNormalName;
	}
	public void setMunicipalityID(String municipalityID) {
		this.municipalityID = municipalityID;
	}
	public void setMunicipalityNormalName(String municipalityNormalName) {
		this.municipalityNormalName = municipalityNormalName;
	}
	public void setCountyID(String countyID) {
		this.countyID = countyID;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public void setGasStationAddress(String gasStationAddress) {
		this.gasStationAddress = gasStationAddress;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public void setUsername(User username) {
		this.user = username;
	}

}
