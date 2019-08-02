package BeanLifecycle;

public interface Coach {

    /**
     * an interface doesn't have any implementation code, it's only a specification.
     * So they simply say, what is available but not how it's implemented.
     * @return The daily workout
     */
    public String getDailyWorkout();

    /**
     * Because our coach already gives daily workouts,     *
     * but now, we want our coach to also give daily fortunes.
     */
    public String getDailyFortune();

}
