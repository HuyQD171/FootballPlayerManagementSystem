package storage;

import java.util.Arrays;

public class DataSeeder {

    public static void seedIfNeeded() {
        FileUtil.ensureDataDirectory();

        if (!FileUtil.isMissingOrEmpty("players.csv")) {
            return;
        }

        FileUtil.writeLines("players.csv", Arrays.asList(
                "P001|Star|Nguyen Van An|24|Vietnam|FW|10|25000000|Active",
                "P002|Regular|Tran Minh Binh|21|Vietnam|GK|1|12000000|Active",
                "P003|Regular|Le Quang Cuong|23|Vietnam|DF|4|14000000|Active",
                "P004|Star|Pham Duc Duy|26|Vietnam|MF|8|22000000|Active"
        ));

        FileUtil.writeLines("training_sessions.csv", Arrays.asList(
                "T001|2026-06-01|Main Stadium|Passing and pressing",
                "T002|2026-06-05|Training Ground A|Defensive shape"
        ));

        FileUtil.writeLines("matches.csv", Arrays.asList(
                "M001|2026-06-10|Saigon FC|League",
                "M002|2026-06-15|Da Nang FC|Friendly"
        ));

        FileUtil.writeLines("attendance.csv", Arrays.asList(
                "A001|P001|T001|true|On time",
                "A002|P002|T001|true|Good effort",
                "A003|P003|T002|false|Medical leave"
        ));

        FileUtil.writeLines("performances.csv", Arrays.asList(
                "PR001|P001|M001|2|1|0|0|90",
                "PR002|P004|M001|1|2|1|0|88",
                "PR003|P003|M002|0|0|0|0|75"
        ));
    }
}
