package ca.co.rufus.androidboilerplate.data.remote;


import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import ca.co.rufus.androidboilerplate.util.Preconditions;
import timber.log.Timber;


public final class SearchQuery {
    private final LocalDate createdSince;

    private SearchQuery(Builder builder) {
        this.createdSince = Preconditions.checkNotNull(builder.createdSince, "createdSince == null");
    }

    @Override
    public String toString() {
        // Returning null here is not ideal, but it lets retrofit drop the query param altogether.
        String result = createdSince == null ? null : "created:>=" + createdSince.toString(ISODateTimeFormat.date());
        Timber.i("Create since: " + createdSince.toString(ISODateTimeFormat.date()));
        return result + " language:java";
    }

    public static final class Builder {
        private LocalDate createdSince;

        public Builder createdSince(LocalDate createdSince) {
            this.createdSince = createdSince;
            return this;
        }

        public SearchQuery build() {
            return new SearchQuery(this);
        }
    }
}
