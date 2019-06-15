package pl.agh.kis.soa;

import javax.faces.bean.*;
import java.io.Serializable;

@ManagedBean(name = "SurveyManager")
@SessionScoped
public class SurveyManager implements Serializable {
    private String name;
    private String email;
    private int age;
    private Gender gender;
    private String education;
    private Double height;
    private Double chest;
    private Double waist;
    private Double bust;
    private String cup;
    private Double hips;
    private int page;
    private String monthlyAmount;
    private String buyPeriods;
    private String[] clothesColors;
    private String[] clothesTypes;

    @ManagedProperty(value="#{adsManager}")
    private AdsManager adsManager;

    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public Gender getGender() { return gender; }
    public Gender[] getGenders() { return Gender.values(); }
    public String getEducation() { return education; }
    public Double getHeight() { return height; }
    public Double getChest() { return chest; }
    public Double getWaist() { return waist; }
    public Double getBust() { return bust; }
    public String getCup() { return cup; }
    public Double getHips() { return hips; }
    public int getPage() { return page; }
    public String getMonthlyAmount() { return monthlyAmount; }
    public String getBuyPeriods() { return buyPeriods; }
    public String[] getClothesColors() { return clothesColors; }
    public String[] getClothesTypes() { return clothesTypes; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) { this.age = age; }
    public void setGender(Gender gender) { this.gender = gender; }
    public void setEducation(String education) { this.education = education; }
    public void setHeight(Double height) { this.height = height; }
    public void setChest(Double chest) { this.chest = chest; }
    public void setWaist(Double waist) { this.waist = waist; }
    public void setBust(Double bust) { this.bust = bust; }
    public void setCup(String cup) { this.cup = cup; }
    public void setHips(Double hips) { this.hips = hips; }
    public void setPage(int page) { this.page = page; }
    public void setMonthlyAmount(String monthlyAmount) { this.monthlyAmount = monthlyAmount; }
    public void setBuyPeriods(String buyPeriods) { this.buyPeriods = buyPeriods; }
    public void setClothesColors(String[] clothesColors) { this.clothesColors = clothesColors; }
    public void setClothesTypes(String[] clothesTypes) { this.clothesTypes = clothesTypes; }
    public void setAdsManager(AdsManager adsManager) { this.adsManager = adsManager; }

    public void next() {
        ++page;
    }

    public void previous() {
        --page;
    }
}
