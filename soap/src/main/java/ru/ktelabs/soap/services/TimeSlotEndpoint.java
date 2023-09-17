package ru.ktelabs.soap.services;


import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.ktelabs.soap.ws.GenerateTimeSlotsRequest;
import ru.ktelabs.soap.ws.GenerateTimeSlotsResponse;
import ru.ktelabs.soap.ws.TimeslotDTO;

import javax.xml.datatype.DatatypeConfigurationException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Endpoint
public class TimeSlotEndpoint {
    public static final String NAMESPACE_URI = "http://ktelabs.ru/soap/ws";

    private final TimeSlotService timeSlotService;

    public TimeSlotEndpoint(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "generateTimeSlotsRequest")
    @ResponsePayload
    public GenerateTimeSlotsResponse generateTimeSlots(@RequestPayload GenerateTimeSlotsRequest request) {
        BigInteger year = request.getYear();
        BigInteger month = request.getMonth();
        BigInteger day = request.getDay();
        BigInteger period = request.getPeriodMinutes();

        Integer yearValue = year == null ? null : year.intValue();
        Integer monthValue = month == null ? null : month.intValue();
        Integer dayValue = day == null ? null : day.intValue();
        Integer periodValue = period == null ? null : period.intValue();

        GenerateTimeSlotsResponse response = new GenerateTimeSlotsResponse();
        List<TimeslotDTO> dtoList = response.getTimeSlots();
        try {
            timeSlotService.generateSlots(yearValue, monthValue, dayValue, periodValue).forEach(item -> {
                try {
                    dtoList.add(item.toDTO());
                } catch (DatatypeConfigurationException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
