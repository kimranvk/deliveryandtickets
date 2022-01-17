package org.abudhabi.delivery.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.abudhabi.delivery.utils.JsonUtils;

@Entity
public class Delivery implements Comparable<Delivery> {
	@Id
	@GeneratedValue
	@Column(name = "delivery_id")
	private Long deliveryId;
	@Column(name = "customer_type")
	private String customerType;
	@Column(name = "delivery_status")
	private String deliveryStatus;
	@Column(name = "expected_delivery_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expectedDeliveryTime;
	@Column(name = "current_distance_from_destination_in_meters")
	private long currentDistanceFromDestinationInMeters;
	@Column(name = "time_to_reach_destination")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeToReachDestination;
	@Column(name = "mean_time_to_prepare_mins")
	private Long meanTimeToPrepareMins;
	private String priority;

	public Delivery() {
		super();
	}

	public Delivery(Long deliveryId, String customerType, String deliveryStatus, Date expectedDeliveryTime,
			long currentDistanceFromDestinationInMeters, Date timeToReachDestination, Long meanTimeToPrepareMins) {
		super();
		this.meanTimeToPrepareMins = meanTimeToPrepareMins;
		this.deliveryId = deliveryId;
		this.customerType = customerType;
		this.deliveryStatus = deliveryStatus;
		this.expectedDeliveryTime = expectedDeliveryTime;
		this.currentDistanceFromDestinationInMeters = currentDistanceFromDestinationInMeters;
		this.timeToReachDestination = timeToReachDestination;
	}

	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Date getExpectedDeliveryTime() {
		return expectedDeliveryTime;
	}

	public void setExpectedDeliveryTime(Date expectedDeliveryTime) {
		this.expectedDeliveryTime = expectedDeliveryTime;
	}

	public long getCurrentDistanceFromDestinationInMeters() {
		return currentDistanceFromDestinationInMeters;
	}

	public void setCurrentDistanceFromDestinationInMeters(long currentDistanceFromDestinationInMeters) {
		this.currentDistanceFromDestinationInMeters = currentDistanceFromDestinationInMeters;
	}

	public Date getTimeToReachDestination() {
		return timeToReachDestination;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setTimeToReachDestination(Date timeToReachDestination) {
		this.timeToReachDestination = timeToReachDestination;
	}

	public Long getMeanTimeToPrepareMins() {
		return meanTimeToPrepareMins;
	}

	public void setMeanTimeToPrepareMins(Long meanTimeToPrepareMins) {
		this.meanTimeToPrepareMins = meanTimeToPrepareMins;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

	@Override
	public int compareTo(Delivery o) {
		// TODO Auto-generated method stub
		return o == null ? 0 : o.priority != null && o.priority.equals("High") ? 1 : -1;
	}
}
