package com.alekhin.javadict.room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Term implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String termTitle;
    public String termContent;

    public Term(int id, String termTitle, String termContent) {
        this.id = id;
        this.termTitle = termTitle;
        this.termContent = termContent;
    }

    protected Term(Parcel in) {
        id = in.readInt();
        termTitle = in.readString();
        termContent = in.readString();
    }

    public static final Creator<Term> CREATOR = new Creator<Term>() {
        @Override
        public Term createFromParcel(Parcel in) {
            return new Term(in);
        }

        @Override
        public Term[] newArray(int size) {
            return new Term[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(termTitle);
        dest.writeString(termContent);
    }
}