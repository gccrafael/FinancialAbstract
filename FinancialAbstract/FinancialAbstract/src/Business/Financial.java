
package Business;

/**
 *
 * @author Rafael Garcia
 */
abstract public class Financial {
    private double amount, rate;
    private int term;
    private boolean built;
    
    public Financial() {
        this(0, 0, 0);
    }
    
    public Financial(double amount, double rate, int term) {
        this.amount = amount;
        this.rate = rate;
        this.term = term;
        built = false;
        
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * @return the term
     */
    public int getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(int term) {
        this.term = term;
    }

    /**
     * @return the built
     */
    protected boolean isBuilt() {
        return built;
    }

    /**
     * @param built the built to set
     */
    protected void setBuilt(boolean built) {
        this.built = built;
    }
    
    public abstract double getResult();
    public abstract String getResultDesc();
    
    public abstract double getBegBal(int month);
    public abstract String getBegBalDesc();
    public abstract double getEndBal(int month);
    public abstract String getEndBalDesc();
    public abstract double getIntFactor(int month);
    public abstract String getIntFactor();
    public abstract String getTitle();
        
    
}
