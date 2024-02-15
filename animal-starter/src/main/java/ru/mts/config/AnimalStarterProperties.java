package ru.mts.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "animal-starter.animal")
public class AnimalStarterProperties {
    private String[] catNames;
    private String[] fishNames;
    private String[] wolfNames;
    private String[] bearNames;


    public void setCatNames(String[] catNames) {
        this.catNames = catNames;
    }

    public void setFishNames(String[] fishNames) {
        this.fishNames = fishNames;
    }

    public void setWolfNames(String[] wolfNames) {
        this.wolfNames = wolfNames;
    }

    public void setBearNames(String[] bearNames) {
        this.bearNames = bearNames;
    }

    public String[] getCatNames() {
        return catNames;
    }

    public String[] getFishNames() {
        return fishNames;
    }

    public String[] getWolfNames() {
        return wolfNames;
    }

    public String[] getBearNames() {
        return bearNames;
    }
}
