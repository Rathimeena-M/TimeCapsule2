package model;

import java.sql.Date;

public class Diary {

    private String userEmail;
    private Date diaryDate;
    private String mood;
    private String diaryText;
    private String photoPath;

    public Diary() {
    }

    public Diary(String userEmail, Date diaryDate, String mood,
                 String diaryText, String photoPath) {

        this.userEmail = userEmail;
        this.diaryDate = diaryDate;
        this.mood = mood;
        this.diaryText = diaryText;
        this.photoPath = photoPath;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getDiaryDate() {
        return diaryDate;
    }

    public void setDiaryDate(Date diaryDate) {
        this.diaryDate = diaryDate;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDiaryText() {
        return diaryText;
    }

    public void setDiaryText(String diaryText) {
        this.diaryText = diaryText;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;

    }
}