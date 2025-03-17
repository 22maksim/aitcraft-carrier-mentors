package com.home.aircraft_carrier_mentors.service.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.aircraft_carrier_mentors.common.data.GeneralData;
import com.home.aircraft_carrier_mentors.exception.MyJsonSerializationExc;
import com.home.aircraft_carrier_mentors.exception.MyNotFoundException;
import com.home.aircraft_carrier_mentors.model.Course;
import com.home.aircraft_carrier_mentors.model.Intern;
import com.home.aircraft_carrier_mentors.model.UserOwner;
import com.home.aircraft_carrier_mentors.model.dto.PaymentCourseOrderRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.PaymentCourseOrderResponseDto;
import com.home.aircraft_carrier_mentors.model.outbox.OrchestratorStatus;
import com.home.aircraft_carrier_mentors.model.outbox.OutboxType;
import com.home.aircraft_carrier_mentors.model.payment.OutboxPayment;
import com.home.aircraft_carrier_mentors.model.payment.PaymentOrderFromCourse;
import com.home.aircraft_carrier_mentors.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentOrderFromCourseRepository paymentOrderFromCourseRepository;
    private final CourseRepository courseRepository;
    private final UserOwnerRepository userOwnerRepository;
    private final ObjectMapper objectMapper;
    private final OutboxPaymentRepository  outboxPaymentRepository;
    private final InternRepository internRepository;

    @Transactional
    @Override
    public PaymentCourseOrderResponseDto payCourse(PaymentCourseOrderRequestDto requestDto) throws JsonProcessingException {
        Course course = courseRepository.findById(requestDto.getCourseId())
                .orElseThrow(() -> new MyNotFoundException("Course not found. Id: " + requestDto.getCourseId()));
        Intern internBuyer = internRepository.findById(requestDto.getBuyerId())
                .orElseThrow(() -> new MyNotFoundException("Intern-buyer not found. Id: " + requestDto.getBuyerId()));

        PaymentOrderFromCourse order = PaymentOrderFromCourse.builder()
                .courseId(course.getId())
                .buyerId(internBuyer.getId())
                .amount(requestDto.getAmount())
                .description(requestDto.getDescription())
                .build();

        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("stage", "create");

        OutboxPayment outboxPayment = OutboxPayment.builder()
                .prefix(GeneralData.PREFIX_ORDER_COURSE)
                .outboxType(OutboxType.ORDER_COURSE)
                .content(objectMapper.writeValueAsString(order))
                .orchestratorStatus(OrchestratorStatus.STARTED)
                .processes(objectMapper.writeValueAsString(jsonMap))
                .build();

        order = paymentOrderFromCourseRepository.save(order);
        outboxPaymentRepository.save(outboxPayment);

        return PaymentCourseOrderResponseDto.builder()
                .id(order.getId())
                .amount(order.getAmount())
                .buyerId(internBuyer.getId())
                .courseId(course.getId())
                .createdAt(order.getCreatedAt())
                .build();
    }

    @Async
    @Transactional
    @Override
    public void findOldOrders() {
        Instant date = Instant.now().plus(2, ChronoUnit.DAYS);
        outboxPaymentRepository.deleteWhereCreatedAtAfter(date);
    }
}
