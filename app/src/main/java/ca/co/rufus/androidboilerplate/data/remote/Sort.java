package ca.co.rufus.androidboilerplate.data.remote;

public enum Sort {
  STARS("stargazers_count"),
  FORKS("forks");

  private final String value;

  Sort(String value) {
    this.value = value;
  }

  @Override public String toString() {
    return value;
  }
}
