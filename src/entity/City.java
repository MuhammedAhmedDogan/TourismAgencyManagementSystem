package entity;

public enum City {
    Adana("Adana"),
    Adiyaman("Adıyaman"),
    Afyonkarahisar("Afyonkarahisar"),
    Agri("Ağrı"),
    Aksaray("Aksaray"),
    Amasya("Amasya"),
    Ankara("Ankara"),
    Antalya("Antalya"),
    Ardahan("Ardahan"),
    Artvin("Artvin"),
    Aydin("Aydın"),
    Balikesir("Balıkesir"),
    Bartin("Bartın"),
    Batman("Batman"),
    Bayburt("Bayburt"),
    Bilecik("Bilecik"),
    Bingol("Bingöl"),
    Bitlis("Bitlis"),
    Bolu("Bolu"),
    Burdur("Burdur"),
    Bursa("Bursa"),
    Canakkale("Çanakkale"),
    Cankiri("Çankırı"),
    Corum("Çorum"),
    Denizli("Denizli"),
    Diyarbakir("Diyarbakır"),
    Duzce("Düzce"),
    Edirne("Edirne"),
    Elazig("Elazığ"),
    Erzincan("Erzincan"),
    Erzurum("Erzurum"),
    Eskisehir("Eskişehir"),
    Gaziantep("Gaziantep"),
    Giresun("Giresun"),
    Gumushane("Gümüşhane"),
    Hakkari("Hakkari"),
    Hatay("Hatay"),
    Igdir("Iğdır"),
    Isparta("Isparta"),
    Istanbul("İstanbul"),
    Izmir("İzmir"),
    Karabuk("Karabük"),
    Karaman("Karaman"),
    Kars("Kars"),
    Kastamonu("Kastamonu"),
    Kayseri("Kayseri"),
    Kirikkale("Kırıkkale"),
    Kirklareli("Kırklareli"),
    Kirsehir("Kırşehir"),
    Kilis("Kilis"),
    Kocaeli("Kocaeli"),
    Konya("Konya"),
    Kutahya("Kütahya"),
    Malatya("Malatya"),
    Manisa("Manisa"),
    Mersin("Mersin"),
    Kahramanmaras("Kahramanmaraş"),
    Mardin("Mardin"),
    Muhla("Muğla"),
    Mus("Muş"),
    Nevsehir("Nevşehir"),
    Nigde("Niğde"),
    Ordu("Ordu"),
    Osmaniye("Osmaniye"),
    Rize("Rize"),
    Sakarya("Sakarya"),
    Samsun("Samsun"),
    Siirt("Siirt"),
    Sinop("Sinop"),
    Sivas("Sivas"),
    Sirnak("Şırnak"),
    Tekirdag("Tekirdağ"),
    Tokat("Tokat"),
    Trabzon("Trabzon"),
    Tunceli("Tunceli"),
    Sanliurfa("Şanlıurfa"),
    Usak("Uşak"),
    Van("Van"),
    Yalova("Yalova"),
    Yozgat("Yozgat"),
    Zonguldak("Zonguldak");

    private final String name;

    City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static City getCity(String str){
        for (City city : City.values()) {
            if (city.getName().equalsIgnoreCase(str)) {
                return city;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}