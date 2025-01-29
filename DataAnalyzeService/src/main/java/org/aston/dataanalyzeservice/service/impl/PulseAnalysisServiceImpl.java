package org.aston.dataanalyzeservice.service.impl;

import com.google.common.collect.Range;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PulseAnalysisServiceImpl {
    private static final Map<Range<Integer>, String> PULSE_CATEGORIES = Map.of(
            Range.closedOpen(0, 60), "Низкий пульс",
            Range.closed(60, 100), "Нормальный пульс",
            Range.closed(101, 120), "Повышенный пульс",
            Range.greaterThan(120), "Умеренный пульс или тахикардия"
    );

    public String determinePulseCategory(int pulse) {
        return PULSE_CATEGORIES.entrySet().stream()
                .filter(entry -> entry.getKey().contains(pulse))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new IllegalArgumentException("Недопустимое значение пульса: " + pulse));
    }
}
