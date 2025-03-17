package com.home.aircraft_carrier_mentors.mapper;

import com.home.aircraft_carrier_mentors.model.dto.PaymentCourseOrderResponseDto;
import com.home.aircraft_carrier_mentors.model.payment.PaymentOrderFromCourse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentOrderFromCourseMapper {

    PaymentCourseOrderResponseDto orderToResponseDto(PaymentOrderFromCourse order);

}
