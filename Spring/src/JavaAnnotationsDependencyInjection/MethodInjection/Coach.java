package JavaAnnotationsDependencyInjection.MethodInjection;

public interface Coach {

    /**
     * an interface doesn't have any implementation code, it's only a specification.
     * So they simply say, what is available but not how it's implemented.
     * @return The daily workout
     */
    public String getDailyWorkout();

    /**
     *
     * @return The daily fortune of the coach
     */
    public String getDailyFortune();
}
