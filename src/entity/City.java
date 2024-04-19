package entity;

public enum City {
    Adana("Adana"),
    Adiyaman("Adıyaman"),
    Afyonkarahisar("Afyonkarahisar"),
    Agri("Ağrı"),
    Amasya("Amasya"),
    Ankara("Ankara"),
    Antalya("Antalya"),
    Artvin("Artvin"),
    Aydin("Aydın"),
    Balikesir("Balıkesir"),
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
    Isparta("Isparta"),
    Mersin("Mersin"),
    Istanbul("İstanbul"),
    Izmir("İzmir"),
    Kars("Kars"),
    Kastamonu("Kastamonu"),
    Kayseri("Kayseri"),
    Kirklareli("Kırklareli"),
    Kirsehir("Kırşehir"),
    Kocaeli("Kocaeli"),
    Konya("Konya"),
    Kutahya("Kütahya"),
    Malatya("Malatya"),
    Manisa("Manisa"),
    Kahramanmaras("Kahramanmaraş"),
    Mardin("Mardin"),
    Muhla("Muğla"),
    Mus("Muş"),
    Nevsehir("Nevşehir"),
    Nigde("Niğde"),
    Ordu("Ordu"),
    Rize("Rize"),
    Sakarya("Sakarya"),
    Samsun("Samsun"),
    Siirt("Siirt"),
    Sinop("Sinop"),
    Sivas("Sivas"),
    Tekirdag("Tekirdağ"),
    Tokat("Tokat"),
    Trabzon("Trabzon"),
    Tunceli("Tunceli"),
    Sanliurfa("Şanlıurfa"),
    Usak("Uşak"),
    Van("Van"),
    Yozgat("Yozgat"),
    Zonguldak("Zonguldak"),
    Aksaray("Aksaray"),
    Bayburt("Bayburt"),
    Karaman("Karaman"),
    Kirikkale("Kırıkkale"),
    Batman("Batman"),
    Sirnak("Şırnak"),
    Bartin("Bartın"),
    Ardahan("Ardahan"),
    Igdir("Iğdır"),
    Yalova("Yalova"),
    Karabuk("Karabük"),
    Kilis("Kilis"),
    Osmaniye("Osmaniye"),
    Duzce("Düzce");

    private final String name;

    City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
