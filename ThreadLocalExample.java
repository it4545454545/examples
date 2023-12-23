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

        NonThreadLocalExample.main(new String[]{});
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
class NonThreadLocalExample {
    private static UserContext userContext = new UserContext("Initial Info");

    public static void main(String[] args) {
        // Запускаем два потока, которые работают с одним и тем же объектом UserContext.
        new Thread(() -> {
            userContext.setUserInfo("Thread 1 Info");
            try {
                Thread.sleep(100); // Задержка для наглядности
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("NonThreadLocalExample Thread 1: " + userContext.getUserInfo());
            // Может вывести "Thread 1 Info" или "Thread 2 Info" в зависимости от тайминга
        }).start();

        new Thread(() -> {
            userContext.setUserInfo("Thread 2 Info");
            try {
                Thread.sleep(100); // Задержка для наглядности
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("NonThreadLocalExample Thread 2: " + userContext.getUserInfo());
            // Может вывести "Thread 1 Info" или "Thread 2 Info" в зависимости от тайминга
        }).start();
    }
}
