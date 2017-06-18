package creditapplication.repository;

import creditapplication.domain.CreditApplicationForm;

public interface CreditApplicationFormRepository {
    CreditApplicationForm findOne(Long id);
}
