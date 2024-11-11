public class Zipcode {
    
    //The following variables are part of the zipToBar method
    private int initZip; // Zipcode given by user
    private String endBar = ""; // Barcode given to user after conversion from Zipcode
    private int[] initZipArray; //int array of input zip values
    private String[] convertedBars; //String array of barcode strings corresponding to zip 
    //for check matching
    private int createCheckZiptoBar;
    private String convertedCheckZiptoBar;
    private int zipSumZipToBar = 0;
    
    //array of the bar formats with index corresponding to value
    private String[] storedBars = new String[] { "||:::", ":::||", "::|:|", "::||:", ":|::|", ":|:|:", ":||::", "|:::|", "|::|:", "|:|::" };
   
    //The following variables are part of the barToZip method
    private String initBar; //Barcode given by user
    private String endZip = ""; //Zipcode given to user after conversion from Barcode
    private int[] convertedZips; //array of Zip ints
    private String[] subString; //array of initBar split into 6 parts to find numbers
    //for check matching
    private boolean checkMatches; 
    private int providedCheckBarToZip;
    private int trueCheckBarToZip;
    private int zipSumBarToZip = 0;
    

    //Zipcode constructor that takes in barcode String
    public Zipcode (String bar){
        initBar = bar;
        barToZip();
    }

    //Zipcode constructor that takes in Zipcode int
    public Zipcode (int zip){
        initZip = zip;
        zipToBar();
    }

    //Method converting Zipcode to barcode  
    public void zipToBar(){

        //populates zip array when there are 2 leading zero's from input
        initZipArray = new int[5];
        if(initZip<1000){
            initZipArray[0]= 0;
            initZipArray[1]= 0;
            for(int i = 4; i > 1; i-- ) {
                initZipArray[i] = initZip % 10;
                initZip /= 10;
            }
        }

        //populates zip array when there is 1 leading zero from input
        else if(initZip<10000){
            initZipArray[0]= 0;
            for(int i = 4; i > 0; i-- ) {
                initZipArray[i] = initZip % 10;
                initZip /= 10;
            }
        }

        //populates zip array when no leading zeros
        else{
            for(int i = 4; i >= 0; i-- ) {
                initZipArray[i] = initZip % 10;
                initZip /= 10;
            }
        }
        
        //string of barcodes
        convertedBars = new String[5];
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 10; j++) {
                if(initZipArray[i]== j ) {
                    convertedBars[i] = storedBars[j];
                }
            }
        }

        //zipsum for check calculation
        for(int i : initZipArray) {
            zipSumZipToBar += i; 
        }

        //calculating check 
        if(zipSumZipToBar % 10 == 0){
            createCheckZiptoBar = 0;
        }
        else {
            createCheckZiptoBar = 10 - (zipSumZipToBar % 10);
        }
        
        //converting check to barcode
        for(int i = 0; i < 10; i++) {
            if(createCheckZiptoBar == i ) {
                convertedCheckZiptoBar = storedBars[i];
            }
        }
       
        //putting string together from barcode array
        for(String s : convertedBars) {
            endBar += s;
        }

        //adding check string to barcode string
        endBar += convertedCheckZiptoBar;
    }

    //Method converting barcode to Zipcode
    public void barToZip(){
        
        //Splitting the barcode into an array of 6 strings (one for each number)
        subString = new String[6];
        for(int i = 0; i < 6; i++) {
        int substart = 1 + i * 5;
        subString[i] = initBar.substring(substart,substart+5);
        }

        //converted barcode string array into numbered Zipcode int array
        convertedZips = new int[5];
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 10; j++) {
                if(subString[i].equals(storedBars[j])) {
                    convertedZips[i] = j;
                }
            }
        }
        
        //converted check number to an int
        for(int i = 0; i < 10; i++) {
            if(subString[5].equals(storedBars[i])){
                providedCheckBarToZip = i;
            }
        }

        //Zipcode number sum to compare check values
        for(int i : convertedZips){
            zipSumBarToZip += i;
        }
        
        //Calculating expected check given Zipcode
        if(zipSumBarToZip % 10 == 0){
            trueCheckBarToZip = 0;
        }
        else {
            trueCheckBarToZip = 10 - (zipSumBarToZip % 10);
        }
        
        //if check does not match returns true; this intiates a print statement in get method
        if(trueCheckBarToZip!=providedCheckBarToZip){
            checkMatches = true;
        }

        //convert int array into String zipcode
        for(int i = 0; i < 5; i++){
            endZip += Integer.toString(convertedZips[i]);
        }
        
    }


    public String getZIPcode(){
        
        //if check does not match, outputs this statement
        if(checkMatches) {
            return endZip + " ; NOTE: Your check number is " + providedCheckBarToZip + " and it does not match what it should be for this the Zipcode: " + trueCheckBarToZip;
        }     
        else {
            return endZip;
        }
    }

    public String getBarcode(){
        return "|" + endBar + "|";
    }
   
}