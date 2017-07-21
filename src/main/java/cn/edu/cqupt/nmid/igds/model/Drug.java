package cn.edu.cqupt.nmid.igds.model;

/**
 * Created by Administrator on 2017/7/1.
 */
public class Drug {
    private String chineseName;
    private String englishName;
    private String category;
    private String imagePath;
    private String introduction;
    private String morphologicalCharacter;
    private String growthHabit;
    private String medicinalValue;

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCharacter() {
        return morphologicalCharacter;
    }

    public void setCharacter(String morphologicalCharacter) {
        this.morphologicalCharacter = morphologicalCharacter;
    }

    public String getGrowthHabit() {
        return growthHabit;
    }

    public void setGrowthHabit(String growthHabit) {
        this.growthHabit = growthHabit;
    }

    public String getMedicinalValue() {
        return medicinalValue;
    }

    public void setMedicinalValue(String medicinalValue) {
        this.medicinalValue = medicinalValue;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "chineseName='" + chineseName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", category='" + category + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", introduction='" + introduction + '\'' +
                ", morphologicalCharacter='" + morphologicalCharacter + '\'' +
                ", growthHabit='" + growthHabit + '\'' +
                ", medicinalValue='" + medicinalValue + '\'' +
                '}';
    }
}
