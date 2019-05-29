package com.example.swoosh;

public class DatabaseEntry {

    private int mID;
    private String mName;
    private int mScore;

    public DatabaseEntry()
    {
        mID = 0;
        mName = "";
        mScore = 0;
    }

    public DatabaseEntry(int id, String name, int score)
    {
        mID = id;
        mName = name;
        mScore = score;
    }

    public void setID(int val)
    {
        mID = val;
    }

    public int getID()
    {
        return mID;
    }

    public void setName(String val)
    {
        mName = val;
    }

    public String getName()
    {
        return mName;
    }

    public void setScore(int val)
    {
        mScore = val;
    }

    public int getScore()
    {
        return mScore;
    }
}
