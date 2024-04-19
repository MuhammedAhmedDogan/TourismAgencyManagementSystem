package entity;

public class Pension {
    private int id;
    private Hotel hotel;
    private PensionType pensionType;

    public enum PensionType {
        Ultra_Her_Sey_Dahil,
        Her_Sey_Dahil,
        Oda_Kahvaltı,
        Tam_Pansiyon,
        Yarım_Pansiyon,
        Sadece_Yatak,
        Alkol_Haric_Full_Credit
    }

    public Pension() {
    }

    public Pension(Hotel hotel, PensionType pensionType) {
        this.hotel = hotel;
        this.pensionType = pensionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public PensionType getPensionType() {
        return pensionType;
    }

    public void setPensionType(PensionType pensionType) {
        this.pensionType = pensionType;
    }
}