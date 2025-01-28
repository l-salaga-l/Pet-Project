package org.aston.dataanalyzeservice.util;

public class PulseAnalyzer {
    public static String getPulseCategory(int pulse) {
        if (pulse < 60) {
            return "Низкий пульс";
        } else if (pulse <= 100) {
            return "Нормальный пульс";
        } else if (pulse <= 120) {
            return "Повышенный пульс";
        } else {
            return "Умеренный пульс или тахикардия";
        }
    }
}
