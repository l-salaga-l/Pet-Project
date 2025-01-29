package org.aston.datacollectorservice.mapper;

import org.aston.datacollectorservice.dto.response.PulseAnalyzeResponseDto;
import org.aston.datacollectorservice.grpc.observer.ResponseObserver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PulseMapper {
    PulseAnalyzeResponseDto toDto(ResponseObserver.PulseData pulseData);
}
