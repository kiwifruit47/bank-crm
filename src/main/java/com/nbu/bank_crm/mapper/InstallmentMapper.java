package com.nbu.bank_crm.mapper;

import com.nbu.bank_crm.dto.response.InstallmentPreviewResponse;
import com.nbu.bank_crm.entity.Installment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstallmentMapper {

    InstallmentPreviewResponse toPreviewResponse(Installment installment);

    List<InstallmentPreviewResponse> toPreviewResponseList(List<Installment> installments);
}
