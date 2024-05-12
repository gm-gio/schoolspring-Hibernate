package com.java.course.schoolspring;

import com.java.course.schoolspring.consolemenu.impl.ConsoleMenuImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class ConsoleMenuRunner implements ApplicationRunner {
    public static final Logger logger = LoggerFactory.getLogger(ConsoleMenuRunner.class);

    private final ConsoleMenuImpl consoleMenu;

    public ConsoleMenuRunner(ConsoleMenuImpl consoleMenu) {
        this.consoleMenu = consoleMenu;
    }

    @Override
    public void run(ApplicationArguments args) {
        consoleMenu.showMainMenu();
    }

}
