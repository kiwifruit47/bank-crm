package com.nbu.bank_crm.service.impl;

import com.nbu.bank_crm.dto.request.CreatePaymentPlanPreviewRequest;
import com.nbu.bank_crm.dto.response.InstallmentPreviewResponse;
import com.nbu.bank_crm.entity.Installment;
import com.nbu.bank_crm.entity.Loan;
import com.nbu.bank_crm.entity.LoanType;
import com.nbu.bank_crm.exception.NotFoundException;
import com.nbu.bank_crm.mapper.InstallmentMapper;
import com.nbu.bank_crm.repository.InstallmentRepository;
import com.nbu.bank_crm.service.InstallmentService;
import com.nbu.bank_crm.service.LoanTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstallmentServiceImpl implements InstallmentService {
    private final InstallmentRepository installmentRepository;
    private final LoanTypeService loanTypeService;
    private final InstallmentMapper installmentMapper;

    @Override
    public List<Installment> getAllByLoanId(long loanId) {
        return installmentRepository.findByLoan_IdOrderByMonth(loanId);
    }

    @Override
    public Installment markAsPaid(long id) {
        Installment installment = getById(id);
        installment.setPaid(true);
        return installmentRepository.save(installment);
    }

    @Override
    public List<InstallmentPreviewResponse> previewPaymentPlant(CreatePaymentPlanPreviewRequest req) {
        List<Installment> installments = generatePaymentPlan(
                req.getAmount(),
                req.getInstallmentsCount(),
                loanTypeService.getByName(req.getLoanType())
        );

        return installmentMapper.toPreviewResponseList(installments);
    }

    @Override
    public Installment getById(long id) {
        Optional<Installment> optional = installmentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException(Installment.class, id);
        }
        return optional.get();
    }


    @Override
    public List<Installment> createPaymentPlan(Loan loan) {
        BigDecimal amount = loan.getAmount();
        int installmentCount = loan.getInstallmentsCount();

        List<Installment> installments = generatePaymentPlan(loan.getAmount(), loan.getInstallmentsCount(), loan.getLoanType());
        for (Installment i : installments) {
            i.setLoan(loan);
        }

        return installmentRepository.saveAll(installments);
    }

    private List<Installment> generatePaymentPlan(BigDecimal amount, int installmentsCount, LoanType loanType) {
        BigDecimal annualInterest = loanType.getInterest();

        BigDecimal monthlyRate = annualInterest
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        BigDecimal onePlusR = BigDecimal.ONE.add(monthlyRate);
        BigDecimal pow = onePlusR.pow(installmentsCount);
        BigDecimal monthlyPayment = amount
                .multiply(monthlyRate)
                .multiply(pow)
                .divide(pow.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);

        List<Installment> installments = new ArrayList<>();
        BigDecimal remainingPrincipal = amount;
        YearMonth currentMonth = YearMonth.now().plusMonths(1);

        for (int i = 1; i <= installmentsCount; i++) {
            BigDecimal interestAmount = remainingPrincipal
                    .multiply(monthlyRate)
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal principalAmount = monthlyPayment
                    .subtract(interestAmount)
                    .setScale(2, RoundingMode.HALF_UP);

            // rounding last installment
            if (i == installmentsCount) {
                principalAmount = remainingPrincipal;
                monthlyPayment = principalAmount.add(interestAmount);
            }

            remainingPrincipal = remainingPrincipal
                    .subtract(principalAmount)
                    .setScale(2, RoundingMode.HALF_UP);

            Installment installment = new Installment(
                    amount,
                    principalAmount,
                    interestAmount,
                    remainingPrincipal,
                    null,
                    false
            );

            installments.add(installment);
            currentMonth = currentMonth.plusMonths(1);
        }
        return installments;
    }
}
