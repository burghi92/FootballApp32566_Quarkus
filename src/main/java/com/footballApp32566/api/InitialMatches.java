package com.footballApp32566.api;

import com.footballApp32566.service.Matches;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.inject.Inject;


@QuarkusMain
public class InitialMatches implements QuarkusApplication {

    @Inject
    MatchController controller;

    @Override
    public int run(String... args) throws Exception {
        Matches match1 = new Matches("Vfb Stuttgart", 2, "1. FC. Köln", 1);
        Matches match2 = new Matches("Borussia Dortmund", 2, "Hertha BSC", 1);
        Matches match3 = new Matches("Borussia Mönchengladbach", 5, "TSG 1899 Hoffenheim", 1);

        controller.addNewMatch(match1);
        controller.addNewMatch(match2);
        controller.addNewMatch(match3);

        Quarkus.waitForExit();

        return 0;
    }
}
