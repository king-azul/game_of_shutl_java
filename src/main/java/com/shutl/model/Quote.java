package com.shutl.model;

public class Quote {
    String pickupPostcode;
    String deliveryPostcode;
    String vehicle;
    Long price;

    /*
     Constructors
    */
    public Quote() {}

    public Quote(String pickupPostcode, String deliveryPostcode) {
        this.pickupPostcode = pickupPostcode;
        this.deliveryPostcode = deliveryPostcode;
    }

    public Quote(String pickupPostcode, String deliveryPostcode, Long price) {
        this.pickupPostcode = pickupPostcode;
        this.deliveryPostcode = deliveryPostcode;
        this.price = price;
    }

    public Quote(String pickupPostcode, String deliveryPostcode, String vehicle) {
        this.pickupPostcode = pickupPostcode;
        this.deliveryPostcode = deliveryPostcode;
        this.vehicle = vehicle;
    }

    public Quote(String pickupPostcode, String deliveryPostcode, String vehicle, long price) {
        this.pickupPostcode = pickupPostcode;
        this.deliveryPostcode = deliveryPostcode;
        this.vehicle = vehicle;
        this.price = price;

        vehicleMarkUp();
    }

    /*
     Functions
    */
    private void vehicleMarkUp() {
      if (this.vehicle.equals("bicycle")) this.price =  (long) (this.price * 1.1); //increases price by 10% for bicycles
      else if (this.vehicle.equals("motorbike"))  this.price =  (long) (this.price * 1.15); //increases price by 15% for motorbikes
      else if (this.vehicle.equals("parcel_car"))  this.price =  (long) (this.price * 1.2); //increases price by 20% for parcel cars
      else if (this.vehicle.equals("small_van")) this.price =  (long) (this.price * 1.3); //increases price by 20% for small vans
      else if (this.vehicle.equals("large_van"))  this.price =  (long) (this.price * 1.4); //increases price by 20% for large vans
    }

    private void vehiclePriceLimit() {//checks price bracket matches vehicle
      if (this.price == null || this.vehicle == null || this.vehicle.equals("large_van"));
      else if (this.vehicle.equals("bicycle") && (this.price > 500)) {
        this.price = (long) (this.price/1.1);//removes vehicle price increase
        setVehicle("motorbike");//sets new vehicle (and adds new vehicle price increase)
        vehiclePriceLimit();
      }
      else if (vehicle.equals("motorbike") && price >  750) {
        this.price = (long) (this.price/1.15);//removes vehicle price increase
        setVehicle("parcel_car");//sets new vehicle (and adds new vehicle price increase)
        vehiclePriceLimit();
      }
      else if (vehicle.equals("parcel_car") && price >  1000) {
        this.price = (long) (this.price/1.2);//removes vehicle price increase
        setVehicle("small_van");//sets new vehicle (and adds new vehicle price increase)
        vehiclePriceLimit();
      }
      else if (vehicle.equals("small_van") && price >  1500) {
        this.price = (long) (this.price/1.3);//removes vehicle price increase
        setVehicle("large_van");//sets new vehicle (and adds new vehicle price increase)
        vehiclePriceLimit();
      }
    }

    /*
     Getter / setter methods
    */
    public String getPickupPostcode() {
        return pickupPostcode;
    }

    public void setPickupPostcode(String pickupPostcode) {
        this.pickupPostcode = pickupPostcode;
    }

    public String getDeliveryPostcode() {
        return deliveryPostcode;
    }

    public void setDeliveryPostcode(String deliveryPostcode) {
        this.deliveryPostcode = deliveryPostcode;
    }

    public String getVehicle() {
      return vehicle;
    }

    public void setVehicle(String vehicle) {
      this.vehicle = vehicle;
      if (price != null) {
        vehicleMarkUp();
        vehiclePriceLimit();
      }
    }

    public Long getPrice() {
      return price;
    }

    public void setPrice(Long price) {
        this.price = price;
        if (this.vehicle != null) vehiclePriceLimit();
    }
}
