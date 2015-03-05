
package business;

/**
 *
 * @author ptd
 */
public class Loan {
    private double prin, rate, mopmt;
    private int term;
    private boolean built;
    private double[] bbal, ichg, ebal;
    
    public Loan(double p, double r, int t) {
        this.prin = p;
        this.rate = r;
        this.term = t;
        mopmt = 0;
        built = false;
        buildLoan();
    }

    public double getPrin() {
        return prin;
    }

    public double getRate() {
        return rate;
    }

    public double getMopmt() {
        if (!built) { buildLoan(); }
        return mopmt;
    }

    public int getTerm() {
        return term;
    }
    public double getBegBal(int mo) {
        if (!built) { buildLoan(); }
        if (mo < 1 || mo > this.term) { return 0; }
        return this.bbal[mo-1];
    }
    public double getIntChg(int mo) {
        if (!built) { buildLoan(); }
        if (mo < 1 || mo > this.term) { return 0; }
        return this.ichg[mo-1];
    }
    public double getEndBal(int mo) {
        if (!built) { buildLoan(); }
        if (mo < 1 || mo > this.term) { return 0; }
        return this.ebal[mo-1];
    }
    private void buildLoan() {
        //calculate Monthly Payment....
        double morate = this.rate / 12.0;
        double denom = Math.pow((1+morate),this.term) - 1;
        this.mopmt = (morate + morate/denom) * this.prin;
        
        this.bbal = new double[this.term];
        this.ichg = new double[this.term];
        this.ebal = new double[this.term];
        
        this.bbal[0] = this.prin;
        for(int i=0; i< this.term; i++) {
            if (i > 0) {
                this.bbal[i] = this.ebal[i-1];
            }
            this.ichg[i] = this.bbal[i] * morate;
            this.ebal[i] = this.bbal[i] + this.ichg[i] - this.mopmt;
        }
    }
}
