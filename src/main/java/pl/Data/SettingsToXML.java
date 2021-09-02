package pl.Data;

public class SettingsToXML {
    public int reminderDays;
    public boolean customLogo;
    public String theme;
    public String filename;

    public SettingsToXML(int reminderDays, boolean customLogo, String filename, String theme) {
        this.reminderDays = reminderDays;
        this.customLogo = customLogo;
        this.theme = theme;
        this.filename = filename;
    }
}
