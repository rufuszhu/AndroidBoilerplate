package ca.co.rufus.androidboilerplate.data.model;

import android.database.Cursor;

import java.util.Arrays;
import java.util.Collection;

import auto.parcel.AutoParcel;
import ca.co.rufus.androidboilerplate.data.local.Db;
import rx.functions.Func1;

/**
 * Created by rzhu on 2/24/2016.
 */
@AutoParcel
public abstract class RepoOwnerJoin {
    private static String OWNER_ID = Owner.TABLE + "." + Owner.ID;
    private static String REPO_ID = Repository.TABLE + "." + Repository.ID;
    public static Collection<String> TABLES = Arrays.asList(Repository.TABLE, Owner.TABLE);

    public static String QUERY = ""
            + "SELECT * "
            + " FROM " + Repository.TABLE
            + " LEFT INNER JOIN " + Owner.TABLE + " ON " + OWNER_ID + " = " + REPO_ID
            + " GROUP BY " + REPO_ID;

    public abstract long id();

    public abstract String name();

    public abstract String description();

    public abstract long watchers();

    public abstract long star();

    public abstract long fork();

    public abstract String url();

    public abstract String updateAt();

    public abstract String login();

    public abstract String avatar();

    public static Func1<Cursor, RepoOwnerJoin> MAPPER = new Func1<Cursor, RepoOwnerJoin>() {
        @Override
        public RepoOwnerJoin call(Cursor cursor) {
            long id = Db.getLong(cursor, Repository.ID);
            String name = Db.getString(cursor, Repository.NAME);
            String description = Db.getString(cursor, Repository.DESCRIPTION);
            long watchers = Db.getLong(cursor, Repository.WATCHERS);
            long star = Db.getLong(cursor, Repository.STARS);
            long fork = Db.getLong(cursor, Repository.FORKS);
            String url = Db.getString(cursor, Repository.HTML_URL);
            String updateAt = Db.getString(cursor, Repository.UPDATED_AT);
            String login = Db.getString(cursor, Owner.LOGIN);
            String avatar = Db.getString(cursor, Owner.AVATAR_URL);

            return new AutoParcel_RepoOwnerJoin(id, name, description, watchers, star, fork, url, updateAt, login, avatar);
        }
    };
}
