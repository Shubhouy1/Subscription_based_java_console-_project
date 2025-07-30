import java.io.Serializable;
import java.time.LocalDate;

public class Subscription implements Serializable {
    private String planName;
    private double amount;
    private LocalDate startDate;

    public Subscription(String planName, double amount, LocalDate startDate) {
        this.planName = planName;
        this.amount = amount;
        this.startDate = startDate;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Plan: " + planName + ", Amount: " + amount + ", Start Date: " + startDate;
    }
}