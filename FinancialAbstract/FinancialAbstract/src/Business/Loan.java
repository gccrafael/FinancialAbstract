
package Business;

/**
 *
 * @author ptd
 */
public class Loan extends Financial {
    public static final String TITLE = "Loan Schedule";
    public static final String AMOUNTDESCRIPTION = "Loan Amount:";
    public static final String RESULTDESCRIPTION = "Monthly Payment:";
    public static final String INTFACTOR = "Int. Charge";
    public static final String BEGBALDESC = "Beg. Loan Bal.";
    public static final String ENDBALDESC = "End. Loan Bal.";
    
    private double mopmt;
    private double[] bbal, ichg, ebal;
    
    public Loan(double amount, double rate, int term) {
        super(amount, rate, term);
        //buildLoan();
    }

    
    @Override
    public double getResult() {
        if (!super.isBuilt()) { buildLoan(); }
        return mopmt;
    }
    @Override
    public String getResultDesc(){
        return Loan.RESULTDESCRIPTION;
    }
    @Override
    public double getBegBal(int month) {
        if (!super.isBuilt()) { buildLoan(); }
        if (month < 1 || month > super.getTerm()) { 
            return 0; 
        }
        return this.bbal[month-1];
    }
    @Override
    public String getBegBalDesc(){
        return Loan.BEGBALDESC;
    }    
    @Override
    public double getIntFactor(int month) {
        if (!super.isBuilt()) { buildLoan(); }
        if (month < 1 || month > super.getTerm()) { return 0; }
        return this.ichg[month-1];
    }
    @Override
    public String getIntFactor(){
        return Loan.INTFACTOR;
    }
    @Override
    public double getEndBal(int month) {
        if (!super.isBuilt()) { buildLoan(); }
        if (month < 1 || month > super.getTerm()) { return 0; }
        return this.ebal[month-1];
    }
    public double getPrinPaid(int month) {
        if (!super.isBuilt()) { buildLoan(); }
        if (month < 1 || month > super.getTerm()) { return 0; }
        return (ebal[month-1] - bbal[month-1]);
    }
    @Override
    public String getEndBalDesc(){
        return Loan.ENDBALDESC;
    }
    @Override
    public String getTitle(){
        return Loan.TITLE;
    }
    private void buildLoan() {
        //calculate Monthly Payment....
        double morate = super.getRate() / 12.0;
        double denom = Math.pow((1+morate),super.getTerm()) - 1;
        this.mopmt = (morate + morate/denom) * super.getAmount();
        
        this.bbal = new double[super.getTerm()];
        this.ichg = new double[super.getTerm()];
        this.ebal = new double[super.getTerm()];
        
        this.bbal[0] = super.getAmount();
        for(int i=0; i< super.getTerm(); i++) {
            if (i > 0) {
                this.bbal[i] = this.ebal[i-1];
            }
            this.ichg[i] = this.bbal[i] * morate;
            this.ebal[i] = this.bbal[i] + this.ichg[i] - this.mopmt;
        }
        super.setBuilt(true);
    }
}
