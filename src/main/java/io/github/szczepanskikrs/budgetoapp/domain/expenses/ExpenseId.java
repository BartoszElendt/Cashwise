package io.github.szczepanskikrs.budgetoapp.domain.expenses;

public record ExpenseId(String value) {
    static ExpenseId newId(String value){
        return new ExpenseId(value);
    }
}
