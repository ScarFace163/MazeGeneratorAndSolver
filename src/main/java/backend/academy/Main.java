package backend.academy;

import backend.academy.service.MainService;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        MainService main = new MainService();
        main.start();
    }
}

