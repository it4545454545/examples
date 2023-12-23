public class ThreadLocalExample {
    private static ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public static void main(String[] args) {
        // Запускаем два потока, каждый из которых изменяет UserContext.
        new Thread(() -> {
            userContext.set(new UserContext("Thread 1 Info"));
            System.out.println("Thread 1: " + userContext.get().getUserInfo());
        }).start();

        new Thread(() -> {
            userContext.set(new UserContext("Thread 2 Info"));
            System.out.println("Thread 2: " + userContext.get().getUserInfo());
        }).start();
    }
}
 class UserContext {
    private String userInfo;

    public UserContext(String info) {
        this.userInfo = info;
    }

    // Getters and setters
    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
