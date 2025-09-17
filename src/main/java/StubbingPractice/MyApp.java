package StubbingPractice;

public class MyApp {
    private final ExternalService service;

    public MyApp(ExternalService service) {
        this.service = service;
    }

    public String getDataWithRetry() {
        try {
            return service.fetchData();
        } catch (RuntimeException e) {
            return service.fetchData(); // retry once
        }
    }
}
