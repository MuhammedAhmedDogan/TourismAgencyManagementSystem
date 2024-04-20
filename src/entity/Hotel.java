package entity;

public class Hotel {
    private int id;
    private String name;
    private City city;
    private String address;
    private String email;
    private String phone;
    private Hotel.Star star;
    private boolean carPark;
    private boolean wifi;
    private boolean pool;
    private boolean fitness;
    private boolean spa;
    private boolean roomService;
    private boolean ultraPension;
    private boolean allInclusivePension;
    private boolean roomBreakfastPension;
    private boolean fullPension;
    private boolean halfPension;
    private boolean onlyBedPension;
    private boolean fullCreditPension;

    public enum Star {
        BIR_YILDIZ("1 Yıldız"),
        IKI_YILDIZ("2 Yıldız"),
        UC_YILDIZ("3 Yıldız"),
        DORT_YILDIZ("4 Yıldız"),
        BES_YILDIZ("5 Yıldız");

        private final String starValue;

        Star(String starValue) {
            this.starValue = starValue;
        }

        public String getStarValue() {
            return starValue;
        }

        public static Star getStar(String str){
            for (Star star : Star.values()) {
                if (star.getStarValue().equalsIgnoreCase(str)) {
                    return star;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return starValue;
        }
    }

    public Hotel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public boolean isCarPark() {
        return carPark;
    }

    public void setCarPark(boolean carPark) {
        this.carPark = carPark;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isFitness() {
        return fitness;
    }

    public void setFitness(boolean fitness) {
        this.fitness = fitness;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isRoomService() {
        return roomService;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }

    public boolean isUltraPension() {
        return ultraPension;
    }

    public void setUltraPension(boolean ultraPension) {
        this.ultraPension = ultraPension;
    }

    public boolean isAllInclusivePension() {
        return allInclusivePension;
    }

    public void setAllInclusivePension(boolean allInclusivePension) {
        this.allInclusivePension = allInclusivePension;
    }

    public boolean isRoomBreakfastPension() {
        return roomBreakfastPension;
    }

    public void setRoomBreakfastPension(boolean roomBreakfastPension) {
        this.roomBreakfastPension = roomBreakfastPension;
    }

    public boolean isFullPension() {
        return fullPension;
    }

    public void setFullPension(boolean fullPension) {
        this.fullPension = fullPension;
    }

    public boolean isHalfPension() {
        return halfPension;
    }

    public void setHalfPension(boolean halfPension) {
        this.halfPension = halfPension;
    }

    public boolean isOnlyBedPension() {
        return onlyBedPension;
    }

    public void setOnlyBedPension(boolean onlyBedPension) {
        this.onlyBedPension = onlyBedPension;
    }

    public boolean isFullCreditPension() {
        return fullCreditPension;
    }

    public void setFullCreditPension(boolean fullCreditPension) {
        this.fullCreditPension = fullCreditPension;
    }
}
