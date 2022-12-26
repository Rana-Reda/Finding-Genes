
public class part1 {
  //method that finds a gene in dna.
  public String findSimpleGene (String dna){
    int startIndex =0;
    int endIndex =0;
    String result = "";
    //get the index of the start codon to be the beginning of the gene
    startIndex = dna.indexOf("ATG");
    //if there is no start codon then there is no valid gene in this dna
    if (startIndex == -1){
      return "";
    }
    //get the index of the first stop codon after the start codon you found
    endIndex = dna.indexOf ("TAA", startIndex +3);
    //if there is no stop codon then no gene
    if (endIndex == -1){
      return "";
    }
    //your gene is the sub string between the start and stop codon you found
    result= dna.substring(startIndex, endIndex+3);
    //if the length of this gene is a multiplication of three then it is a valid gene
    if (result.length() %3 ==0){
      return result;
    }
    else {
      return "";
    }
  }
  //method to call findSimpleGene method with different dnas to test it
  public void testSimpleGene(){
    String dna = "AAATGCCCTAACTAGATTAAGAAACC ";
    System.out.println(" DNA IS " + dna);
    String gene = findSimpleGene(dna);
    System.out.println("GENE IS " + gene); 
    dna = "CGATGGTTG ";
    System.out.println(" DNA IS " + dna);
    gene = findSimpleGene(dna);
    System.out.println("GENE IS " + gene); 
    dna = "CCAATTAA ";
    System.out.println(" DNA IS " + dna);
    gene = findSimpleGene(dna);
    System.out.println("GENE IS " + gene); 
    dna = "ATGGCTCGTAA ";
    System.out.println(" DNA IS " + dna);
    gene = findSimpleGene(dna);
    System.out.println("GENE IS " + gene); 

  }
  public static void main(String[] args){
    part1 pr = new part1();
    pr.testSimpleGene();
  }
}
