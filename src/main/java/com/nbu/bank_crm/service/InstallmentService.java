package com.nbu.bank_crm.service;

import com.nbu.bank_crm.dto.request.CreatePaymentPlanPreviewRequest;
import com.nbu.bank_crm.dto.response.InstallmentPreviewResponse;
import com.nbu.bank_crm.entity.Installment;
import com.nbu.bank_crm.entity.Loan;

import java.util.List;

public interface InstallmentService {
    List<Installment> getAllByLoanId(long loanId);
    Installment markAsPaid(long id);
    List<InstallmentPreviewResponse> previewPaymentPlant(CreatePaymentPlanPreviewRequest req);
    Installment getById(long id);
    List<Installment> createPaymentPlan(Loan loan);
}
