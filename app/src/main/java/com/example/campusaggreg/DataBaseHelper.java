package com.example.campusaggreg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    List<NewsArticle> articles = new ArrayList<>();
    String title, desc, source,url;
    int upload, count = 0;

    public DataBaseHelper(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ARTICLES(Title varchar(40),Description varchar(200),Source varchar(40),Upload integer,Imgurl varchar(200))");
        db.execSQL("INSERT INTO ARTICLES values ('New Library Updates ','The new library on campus, would be inaugrated soon, as per the trust statement. Looking at a potential date in August 2023','Admin',2,'https://www.sirmvit.edu/wp-content/uploads/2020/12/Sirmvit-advantage-thumb-5.jpg')");
        db.execSQL("INSERT INTO ARTICLES values ('SDP on Graph Theory','Department of MCA conducts a One day SDP on Graph Theory and its applications - 16/05/2023 9am. Mr. Manjunath has been invited as the resourcce person','MCA Dept',1,'https://docs.google.com/uc?id=1v7jYnrX8Y7eHeCy5m1IVtFAzgJa5zhmn')");
        db.execSQL("INSERT INTO ARTICLES values ('FDP on AI,ML & DS in Research in Biotech','Dept of BT is organising VGST sponsored FDP on “Applications of AI, ML and Data Science in Biological Research “ from 5.7.23 to 8.7.23','CSE Dept',1,'https://docs.google.com/uc?id=1NlFrxzMN6x4dioM44uxkIPkRYwDqxrQK')");
        db.execSQL("INSERT INTO ARTICLES values ('Parent Teacher Meeting','Department of CSE has conducted a Parent Teacher Meeting on Feb 24 - 2013. Parents had attended this meeting with utmost enthusiasm','CSE Dept',1,'https://docs.google.com/uc?id=1alaA_yUXhsy_c9h2ZprAIuHghO9G5Uup')");
        db.execSQL("INSERT INTO ARTICLES values ('Research Grant Awarded','A prominent faculty member receives a substantial research grant to further their groundbreaking work.','CSE Dept',1,'')");
        db.execSQL("INSERT INTO ARTICLES values ('New Student Center Open','The university inaugurates a state-of-the-art student center, providing a hub for student activities.','Student Organization',0,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Campus Sustainability Week','The university hosts a week-long event focused on promoting sustainability and eco-friendly practices.','Admin',2,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Dormitory Renovations','Several dormitories undergo extensive renovations, offering improved living spaces for students.','Admin',2,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Guest Speaker Series','Renowned experts from various fields deliver captivating talks as part of the campus guest speaker series.','ISE Dept',1,'')");
        db.execSQL("INSERT INTO ARTICLES values ('STEM Scholarship Program','A new scholarship program is launched, aimed at supporting and encouraging students pursuing STEM disciplines.','CSE Dept',1,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Campus-wide WiFi Upgrade','The university invests in upgrading its WiFi infrastructure, ensuring fast and reliable internet access across campus.','Admin',2,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Cultural Diversity Workshop','A workshop is organized to promote cultural awareness and inclusivity among students and staff members.','Susamskriti',0,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Alumni Homecoming Event','The annual alumni homecoming event brings together graduates from various years to reconnect and reminisce.','Admin',2,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Health and Wellness Fair','A health fair is organized on campus, providing students with access to valuable resources and information related to physical and mental well-being.','Phy Edu Dept',1,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Student Leadership Summit','A leadership summit is organized, bringing together student leaders from different organizations for skill-building and collaboration.','Student Organization',0,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Community Service Day','Students, faculty, and staff participate in a day of community service, volunteering in local neighborhoods and organizations.','CSE Dept',1,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Career Fair Success','The annual career fair attracts a record number of employers, providing students with valuable networking and job opportunities.','Placement',1,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Campus Art Exhibition','An art exhibition featuring works by talented students and renowned artists takes place in the campus gallery.','Susamskriti',0,'')");
        db.execSQL("INSERT INTO ARTICLES values ('New Campus Shuttle Service','The university introduces a new shuttle service, enhancing transportation options for students commuting to and from campus.','Admin',2,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Sustainable Food Initiative','A sustainable food initiative is launched, promoting locally sourced, organic options in the campus dining facilities.','Hostel',2,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Graduation Ceremony Highlights','The graduation ceremony celebrates the achievements of the graduating class, featuring inspiring speeches and memorable moments.','Student Organization',0,'')");
        db.execSQL("INSERT INTO ARTICLES values ('Innovation Hackathon','Students participate in a hackathon, collaborating on innovative projects and presenting their solutions to real-world challenges.','Student Organization',0,'')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists ARTICLES");
        onCreate(db);
    }

    public List<NewsArticle> ReadRecords(int choose) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM ARTICLES", null);
            while (cursor.moveToNext()) {
                title = cursor.getString(0);
                desc = cursor.getString(1).substring(0,50)+" ...";
                source = cursor.getString(2);
                upload = cursor.getInt(3);
                url = cursor.getString(4);
                if (choose == -1)
                    articles.add(new NewsArticle(title, desc, source, upload,url));
                else if (choose == upload)
                    articles.add(new NewsArticle(title, desc, source, choose,url));
            }

        } catch (Exception ex) {
            articles.add((new NewsArticle(ex.getMessage(),ex.getLocalizedMessage(),"dwa",4,"dsfs")));
        }
        return articles;
    }

    public NewsArticle ClickViewRecord(int pos,int choose) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ARTICLES", null);
        while (cursor.moveToNext()) {
            title = cursor.getString(0);
            desc = cursor.getString(1);
            source = cursor.getString(2);
            upload = cursor.getInt(3);
            url = cursor.getString(4);
            if (choose == -1)
                count++;
            else if (choose == upload)
                count++;
            if (pos == count - 1) break;
        }
        NewsArticle article = new NewsArticle(title, desc, source, upload,url);
        return article;
    }
}

