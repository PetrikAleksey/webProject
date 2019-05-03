package spring.DAO.Model.Enum;

public enum Position {
    FINANCIAL_ANALYST("Финансовый аналитик"),
    PERSONAL_FINANCIAL_ADVISOR("Персональный финансовый консультант"),
    ACCOUNTANT("Бухгалтер"),
    AUDITORS("Аудитор"),
    LOAN_OFFICER("Кредитный эксперт"),
    BANK_TELLERS("Операционист");

    private String title;

    Position(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
