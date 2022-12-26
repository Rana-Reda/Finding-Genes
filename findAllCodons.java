
public class findAllCodons {
  // This method returns the index of the first occurrence of stopCodon that appears past startIndex  
  public int findStopCodon(String dna, int startIndex, String stopCodon){
    //get the index of stopcodon
    int currIndex= dna.indexOf(stopCodon, startIndex+3);
    while (currIndex!= -1){
      int diff= currIndex - startIndex;
      if( diff%3==0){
        return currIndex;
      }
      else{
        currIndex= dna.indexOf(stopCodon, currIndex+1);
      }
    }
    //if the gene doesn't have a valid length return the length of dna 
    return dna.length();
  }
  //Method to find the gene that starts with thr startcodon and the closest stopcodon to it
  public String findGene(String dna, int where){
    int startIndex = dna.indexOf("ATG", where);
    if (startIndex== -1){
      return "";
    }
    //find index for every stopcodon of the three stopcodons
    int taaIndex = findStopCodon(dna, startIndex, "TAA");
    int tagIndex = findStopCodon(dna, startIndex, "TAG");
    int tgaIndex = findStopCodon(dna, startIndex, "TGA");
    //create a variable to put the index of the stopcodon while comparing 
    int minIndex=0;
    //if there is no TAA then you will set minIndex to the index of TAG
   //if if there is TAA and TAG then set minIndex to the smallest of the two
    if(taaIndex== -1 || tagIndex!= -1 && tagIndex < taaIndex){
      minIndex= tagIndex;
    }
    else{
      minIndex= taaIndex;
    }
    //take minIndex and do the same thing with the last stopcodon TGA
    if(minIndex==-1 || tgaIndex != -1 && tgaIndex<minIndex){
      minIndex= tgaIndex;
    }
    //if minIndex = the length of the dna then there is no stopcodon and you should retyrn the empty string
    if (minIndex == dna.length()){
      return "";
    }
    //get the gene between the startcodon and the closest stopcodon you calculated
    return dna.substring(startIndex, minIndex+3);
  }
  //method to print all genes existing in a dna by calling findGene method
  public void printAllGenes(String dna){
     int startIndex=0;
     while(true){
       String currentGene= findGene(dna, startIndex);
       if (currentGene.isEmpty()){
         break;
       }
       System.out.println(currentGene);
       //after printing the gene update startIndex to print the next gene after this one
       startIndex=dna.indexOf(currentGene,startIndex)+ currentGene.length();  
     }
  } 
  //method to test printgenes 
  public void testOn(String dna){
    System.out.println("testing print all genes on:" +dna);
    printAllGenes(dna);
  }
  public void test(){
    testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAAA");
  }
  public void testFindStopCodon(){
    String dna="ATGCGTAAGTAA ";
    int result = findStopCodon(dna,0,"TAA" );
    System.out.println("result ="+ result);
    dna="CGATCGGTAACGTAACGTAA ";
    result = findStopCodon(dna,2,"TAA" );
    System.out.println("result ="+ result);
    dna="ATGCGTAATAA ";
    result = findStopCodon(dna,0,"TAA" );
    System.out.println("result ="+ result);
  }
  public static void main(String[] args){
    findAllCodons pr= new findAllCodons();
    pr.test();
  }
}
