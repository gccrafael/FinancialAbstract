
package business;

/**
 *
 * @author ptd
 */
public class Annuity {
    private double deposit, rate;
    private int term;
    private boolean built;  //whether fv has been built
    //private double fv;     //final value of annuity
    private double[] bbal, iearn, ebal;
    
    public Annuity(double d, double r, int term) {
        //constructor for class
        this.deposit = d;
        this.rate = r;
        this.term = term;
        this.built = false;
        //this.fv = 0;
        calcAnnuity();
    }
    public double getDeposit() {
        return this.deposit;
    }
    public double getRate() {
        return this.rate;
    }
    public int getTerm() {
        return this.term;
    }
    public double getFinalValue() {
        if (!built) {
            calcAnnuity();
        }
        return this.ebal[this.term-1];
    }
    public double getBegBal(int mo) {
        if (!built) { calcAnnuity(); }
        if (mo < 1 || mo > this.term) { return 0; }
        return this.bbal[mo-1];
    }
    public double getIntEarned(int mo) {
        if (!built) { calcAnnuity(); }
        if (mo < 1 || mo > this.term) { return 0; }
        return this.iearn[mo-1];
    }
    public double getEndBal(int mo) {
        if (!built) { calcAnnuity(); }
        if (mo < 1 || mo > this.term) { return 0; }
        return this.ebal[mo-1];
    }
    private void calcAnnuity() {
        //internal logic for building an annuity...
        //this.fv = 0;
        //double intearned=0;
        this.bbal = new double[this.term];
        this.iearn = new double[this.term];
        this.ebal = new double[this.term];
        
        bbal[0] = 0;
        for (int i=0; i<this.term; i++) {
            if (i > 0) {
                this.bbal[i] = this.ebal[i-1];
            }
            this.iearn[i] = (this.bbal[i] + this.deposit )* (this.rate/12.0);
            this.ebal[i] = this.bbal[i] + this.iearn[i] + this.deposit;
            //this.fv = this.fv + intearned + this.deposit;
        }
        this.built = true;
    }
    
}
