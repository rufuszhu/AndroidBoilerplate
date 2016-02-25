package ca.co.rufus.androidboilerplate.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import auto.parcel.AutoParcel;
import ca.co.rufus.androidboilerplate.data.local.Db;
import rx.functions.Func1;

@AutoParcel
@AutoGson
public abstract class Repository {
    public static final String TABLE = "repository";

    public static final String ID = "_id";
    public static final String OWNER_ID = "user_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String WATCHERS = "watchers";
    public static final String STARS = "stargazers_count";
    public static final String FORKS = "forks";
    public static final String HTML_URL = "html_url";
    public static final String UPDATED_AT = "updated_at";

    public static final String QUERY = "SELECT * FROM " + Repository.TABLE;

    public abstract long id();

    public abstract long user_id();

    public abstract String name();

    public abstract String description();

    public abstract long watchers();

    public abstract long forks();

    public abstract long stargazers_count();

    public abstract String html_url();

    public abstract String updated_at();

    public abstract Owner owner();

    public static Repository create(long id, long owner_id, String name, String description, long watchers, long forks, long stars, String html_url, String updated_at, Owner owner){
        return new AutoParcel_Repository(id, owner_id, name, description, watchers, forks, stars, html_url, updated_at, owner);
    }

    public static Func1<Cursor, List<Repository>> MAP = new Func1<Cursor, List<Repository>>() {
        @Override
        public List<Repository> call(final Cursor cursor) {
            try {
                List<Repository> values = new ArrayList<>(cursor.getCount());

                while (cursor.moveToNext()) {
                    long id = Db.getLong(cursor, ID);
                    long owner_id = Db.getLong(cursor, OWNER_ID);
                    String name = Db.getString(cursor, NAME);
                    String description = Db.getString(cursor, DESCRIPTION);
                    long watchers = Db.getLong(cursor, WATCHERS);
                    long forks = Db.getLong(cursor, FORKS);
                    long stars = Db.getLong(cursor, STARS);
                    String html_url = Db.getString(cursor, HTML_URL);
                    String updated_at = Db.getString(cursor, UPDATED_AT);
                    values.add(new AutoParcel_Repository(id, owner_id, name, description, watchers, forks, stars, html_url, updated_at, null));
                }
                return values;
            } finally {
                cursor.close();
            }
        }
    };

    public static ContentValues toContentValues(Repository repository) {
        ContentValues values = new ContentValues();
        values.put(ID, repository.id());
        values.put(NAME, repository.name());
        values.put(OWNER_ID, repository.owner().id());
        values.put(DESCRIPTION, repository.description());
        values.put(STARS, repository.stargazers_count());
        values.put(FORKS, repository.forks());
        values.put(HTML_URL, repository.html_url());
        values.put(UPDATED_AT, repository.updated_at());
        return values;
    }

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder name(String name) {
            values.put(NAME, name);
            return this;
        }

        public Builder owner(long owner) {
            values.put(OWNER_ID, owner);
            return this;
        }

        public Builder description(String description) {
            values.put(DESCRIPTION, description);
            return this;
        }

        public Builder stars(long stars) {
            values.put(STARS, stars);
            return this;
        }

        public Builder forks(long forks) {
            values.put(FORKS, forks);
            return this;
        }

        public Builder htmlUrl(String htmlUrl) {
            values.put(HTML_URL, htmlUrl);
            return this;
        }

        public Builder updatedAt(String updatedAt) {
            values.put(UPDATED_AT, updatedAt);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}
