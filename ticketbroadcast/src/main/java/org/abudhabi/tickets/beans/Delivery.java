package org.abudhabi.tickets.beans;

import java.util.Date;

import org.abudhabi.tickets.utils.JsonUtils;

public class Delivery /*implements Comparable<Delivery>*/ {
	private Long deliveryId;
	private String customerType;
	private String deliveryStatus;
	private Date expectedDeliveryTime;
	private long currentDistanceFromDestinationInMeters;
	private Date timeToReachDestination;
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

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result
//				+ (int) (currentDistanceFromDestinationInMeters ^ (currentDistanceFromDestinationInMeters >>> 32));
//		result = prime * result + ((customerType == null) ? 0 : customerType.hashCode());
//		result = prime * result + ((deliveryId == null) ? 0 : deliveryId.hashCode());
//		result = prime * result + ((deliveryStatus == null) ? 0 : deliveryStatus.hashCode());
//		result = prime * result + ((expectedDeliveryTime == null) ? 0 : expectedDeliveryTime.hashCode());
//		result = prime * result + ((timeToReachDestination == null) ? 0 : timeToReachDestination.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Delivery other = (Delivery) obj;
//		if (currentDistanceFromDestinationInMeters != other.currentDistanceFromDestinationInMeters)
//			return false;
//		if (customerType == null) {
//			if (other.customerType != null)
//				return false;
//		} else if (!customerType.equals(other.customerType))
//			return false;
//		if (deliveryId == null) {
//			if (other.deliveryId != null)
//				return false;
//		} else if (!deliveryId.equals(other.deliveryId))
//			return false;
//		if (deliveryStatus == null) {
//			if (other.deliveryStatus != null)
//				return false;
//		} else if (!deliveryStatus.equals(other.deliveryStatus))
//			return false;
//		if (expectedDeliveryTime == null) {
//			if (other.expectedDeliveryTime != null)
//				return false;
//		} else if (!expectedDeliveryTime.equals(other.expectedDeliveryTime))
//			return false;
//		if (timeToReachDestination == null) {
//			if (other.timeToReachDestination != null)
//				return false;
//		} else if (!timeToReachDestination.equals(other.timeToReachDestination))
//			return false;
//		return true;
//	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

//	@Override
//	public int compareTo(Delivery o) {
//		// TODO Auto-generated method stub
//		return o == null ? 0 : o.priority != null && o.priority.equals("High") ? 1 : -1;
//	}
}
