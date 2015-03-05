
package Business;

/**
 *
 * @author ptd
 */
public class Annuity extends Financial {    
    public static final String AMOUNTDESCRIPTION = "Deposit Amount:";
    public static final String RESULTDESCRIPTION = "Final Value:";
    public static final String INTFACTORDESC = "Int. Earned";
    public static final String BEGBALDESC = "Beg. Annuity Value";
    public static final String ENDBALDESC = "End. Annuity Value";
    public static final String TITLE = "Annuity Schedule";
  
    
    private double[] bbal, iearn, ebal;
    
    public Annuity() {
        super();
    }
    
    public Annuity(double amount, double rate, int term) {
        super(amount, rate, term);
        //calcAnnuity();
    }
    @Override
    public double getResult() {
        if (!super.isBuilt()) {
            calcAnnuity();
        }
        return this.ebal[super.getTerm()-1];
    }
    @Override
    public double getBegBal(int month) {
        if (!super.isBuilt()) { calcAnnuity(); }
        if (month < 1 || month > super.getTerm()) { return 0; }
        return this.bbal[month-1];
    }
    @Override
    public double getIntFactor(int month) {
        if (!super.isBuilt()) { calcAnnuity(); }
        if (month < 1 || month > super.getTerm()) { return 0; }
        return this.iearn[month-1];
    }
    @Override
    public double getEndBal(int month) {
        if (!super.isBuilt()) { calcAnnuity(); }
        if (month < 1 || month > super.getTerm()) { return 0; }
        return this.ebal[month-1];
    }
    private void calcAnnuity() {
        //internal logic for building an annuity...
        //this.fv = 0;
        //double intearned=0;
        this.bbal = new double[super.getTerm()];
        this.iearn = new double[super.getTerm()];
        this.ebal = new double[super.getTerm()];
        
        bbal[0] = 0;
        for (int i=0; i<super.getTerm(); i++) {
            if (i > 0) {
                this.bbal[i] = this.ebal[i-1];
            }
            this.iearn[i] = (this.bbal[i] + super.getAmount() )* (super.getRate()/12.0);
            this.ebal[i] = this.bbal[i] + this.iearn[i] + super.getAmount();
            //this.fv = this.fv + intearned + this.deposit;
        }
        super.setBuilt(true);
    }

    @Override
    public String getResultDesc() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return Annuity.RESULTDESCRIPTION;
    }

    @Override
    public String getBegBalDesc() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return Annuity.BEGBALDESC; 
    }

    @Override
    public String getEndBalDesc() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return Annuity.ENDBALDESC;
    }

    @Override
    public String getIntFactor() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return Annuity.INTFACTORDESC;
    }

    @Override
    public String getTitle() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return Annuity.TITLE;
    }
    
}
