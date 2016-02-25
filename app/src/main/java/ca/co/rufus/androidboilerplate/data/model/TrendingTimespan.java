package ca.co.rufus.androidboilerplate.data.model;


import org.joda.time.LocalDate;
import org.joda.time.Period;

enum TrendingTimespan {
  DAY("today", Period.days(1)),
  WEEK("last week",Period.weeks(1)),
  MONTH("last month",Period.months(1));

  private final String name;
  private Period duration;

  TrendingTimespan(String name, Period duration) {
    this.name = name;
    this.duration = duration;
  }

  public LocalDate createdSince() {
    return LocalDate.now().minus(duration);
  }

  @Override public String toString() {
    return name;
  }
}
