


public class part2 {
    //this method gets a gene from a given dna 
    public String findSimpleGene (String dna ,String startCodon , String stopCodon){
    int startIndex =0;
    int endIndex =0;
    String result = "";
    //if the dna is uppercase letters
    if (dna.equals(dna.toUpperCase())){
      //get the index of the startcodon
      startIndex = dna.indexOf(startCodon);
      if (startIndex == -1){
        return "";
      }
      //get index of stopcodon
      endIndex = dna.indexOf (stopCodon, startIndex +3);
      if (endIndex == -1){
        return "";
      }
      //extract your gene from dna
      result= dna.substring(startIndex, endIndex+3);
    }
    //if the dna is lowercase letters
    if (dna.equals(dna.toLowerCase())) {
      startIndex = dna.indexOf(startCodon.toLowerCase());
      if (startIndex == -1){
        return "";
      }
      endIndex = dna.indexOf (stopCodon.toLowerCase(), startIndex+3);
      if (endIndex == -1){
        return "";
      }
      result= dna.substring(startIndex, endIndex+3);
    
    }
    if (result.length() %3 ==0){
      return result;
    }
    else {
       return "";
    }
  }
  //method to call findSimpleGene method with different dnas to test it
  public void testSimpleGene(){
    String dna = "AATGCGTTAATATGGT ";
    String startCodon = "ATG";
    String stopCodon = "TAA";
    System.out.println(" DNA IS " + dna);
    String gene = findSimpleGene(dna, startCodon, stopCodon);
    System.out.println("GENE IS " + gene); 
    dna = "aatgcgttaatatggt ";
    System.out.println(" DNA IS " + dna);
    gene = findSimpleGene(dna, startCodon, stopCodon);
    System.out.println("GENE IS " + gene); 
    dna = "CGATGGTTG ";
    System.out.println(" DNA IS " + dna);
    gene = findSimpleGene(dna, startCodon, stopCodon);
    System.out.println("GENE IS " + gene); 
    dna = "CCAATTAA ";
    System.out.println(" DNA IS " + dna);
    gene = findSimpleGene(dna, startCodon, stopCodon);
    System.out.println("GENE IS " + gene); 
    dna = "ATGGCTCGTAA ";
    System.out.println(" DNA IS " + dna);
    gene = findSimpleGene(dna, startCodon, stopCodon);
    System.out.println("GENE IS " + gene); 
  }
  public static void main(String[] args){
    part2 pr = new part2();
    pr.testSimpleGene();
  }
    
}
