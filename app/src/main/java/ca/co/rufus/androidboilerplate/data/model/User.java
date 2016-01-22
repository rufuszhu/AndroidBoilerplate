package ca.co.rufus.androidboilerplate.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import auto.parcel.AutoParcel;
import ca.co.rufus.androidboilerplate.data.local.Db;
import rx.functions.Func1;

@AutoParcel
public abstract class User {
    public static final String TABLE = "user";

    public static final String ID = "_id";
    public static final String LOGIN = "login";
    public static final String AVATAR_URL = "avatar_url";

    public abstract long id();

    public abstract String login();

    public abstract String avatar_url();

    public static final Func1<Cursor, User> MAPPER = new Func1<Cursor, User>() {
        @Override
        public User call(Cursor cursor) {
            long id = Db.getLong(cursor, ID);
            String description = Db.getString(cursor, LOGIN);
            String complete = Db.getString(cursor, AVATAR_URL);
            return new AutoParcel_User(id, description, complete);
        }
    };

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder id(long id) {
            values.put(ID, id);
            return this;
        }

        public Builder login(String description) {
            values.put(LOGIN, description);
            return this;
        }

        public Builder avatar_url(String complete) {
            values.put(AVATAR_URL, complete);
            return this;
        }

        public ContentValues build() {
            return values; // TODO defensive copy?
        }
    }
}
