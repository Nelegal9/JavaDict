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
    public Boolean termFavorite;

    public Term(int id, String termTitle, String termContent, Boolean termFavorite) {
        this.id = id;
        this.termTitle = termTitle;
        this.termContent = termContent;
        this.termFavorite = termFavorite;
    }

    protected Term(Parcel in) {
        id = in.readInt();
        termTitle = in.readString();
        termContent = in.readString();
        byte tmpTermFavorite = in.readByte();
        termFavorite = tmpTermFavorite == 0 ? null : tmpTermFavorite == 1;
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
        dest.writeByte((byte) (termFavorite == null ? 0 : termFavorite ? 1 : 2));
    }
}