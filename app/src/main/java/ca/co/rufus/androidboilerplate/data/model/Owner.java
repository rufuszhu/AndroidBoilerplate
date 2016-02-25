package ca.co.rufus.androidboilerplate.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import auto.parcel.AutoParcel;
import ca.co.rufus.androidboilerplate.data.local.Db;
import rx.functions.Func1;

@AutoParcel
@AutoGson
public abstract class Owner {
    public static final String TABLE = "owner";

    public static final String ID = "_id";
    public static final String LOGIN = "login";
    public static final String AVATAR_URL = "avatar_url";

    public static final String USER_QUERY = "SELECT * FROM " + Owner.TABLE + " WHERE " + Owner.ID + " = ?";

    public abstract long id();

    public abstract String login();

    public abstract String avatar_url();

    public static Owner create(long id, String description, String avatar_url){
        return new AutoParcel_Owner(id, description, avatar_url);
    }

    public static final Func1<Cursor, Owner> MAPPER = new Func1<Cursor, Owner>() {
        @Override
        public Owner call(Cursor cursor) {
            long id = Db.getLong(cursor, ID);
            String description = Db.getString(cursor, LOGIN);
            String url = Db.getString(cursor, AVATAR_URL);
            return new AutoParcel_Owner(id, description, url);
        }
    };

    public static ContentValues toContentValues(Owner owner) {
        ContentValues values = new ContentValues();
        values.put(ID, owner.id());
        values.put(LOGIN, owner.login());
        values.put(AVATAR_URL, owner.avatar_url());
        return values;
    }

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
