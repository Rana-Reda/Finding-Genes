
import edu.duke.*;
public class part1 {
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
  //this method gets all genes in a dna and stores them in a storageresource
  public StorageResource getAllGenes(String dna){
    int startIndex=0;
    //create a new storageresource named geneList to store your genes in
    StorageResource geneList= new StorageResource();
    while(true){
       String currentGene= findGene(dna, startIndex);
       if (currentGene.isEmpty()){
         break;
       }
       geneList.add(currentGene);
       startIndex=dna.indexOf(currentGene,startIndex)+ currentGene.length();  
    }
    return geneList;

  } 
  //this methid counts the genes in a dna
  public int countGenes(String dna){
    int startIndex=0;
    int count=0;
    //iterate over the dna
    while(true){
       //call the methid findGene to get the gene
       String currentGene=findGene(dna, startIndex);
       //if there is no gene stop
       if(currentGene.isEmpty()){
         break;
       }
       //else increment the count for genes
       count+=1;
       //update startindex to get the next gene
       startIndex=dna.indexOf(currentGene, startIndex)+ currentGene.length();

    }
    return count;

  } 
  //this method returns the ratio of C’s and G’s in dna as a fraction of the entire strand of DNA. 
  public float cgRatio(String dna){
    int countCg=0;
    //iterate over the dna characters
    for(int i=0; i< dna.length(); i++){
      if (dna.charAt(i)== 'C' || dna.charAt(i)== 'c' || dna.charAt(i)== 'G' || dna.charAt(i)== 'c'){
        countCg++;
      }
    }
    float cgRatio= (float)countCg / dna.length();
    return cgRatio;
  }
  //method to test the method ny passing a dna to it
  public void testOn(String dna){
    System.out.println("testing print all genes on:" +dna);
    //create a storageresource and put the output genes from method getAllGenes in
    StorageResource genes= getAllGenes(dna);
    //get the ratio by calling the method and print it
    float ratio= cgRatio(dna);
    System.out.println("the cg ratio in dna: " +ratio);
  }
  //a method that returns the number of times the codon CTG appears in dna. 
  public int countCTG(String dna){
     int ctgCount=0;
     int index= dna.indexOf("CTG");
     while(true){
       if(index == -1){
         break;
       }
       else {
         ctgCount++;
       }
       index=dna.indexOf("CTG", index+3);
     }
     return ctgCount;
  }
  // This method processes all the strings in sr to find out information about them. 
  public void processGenes(StorageResource sr){
    int longThan=0;
    int cgLongThan=0;
    int currLength=0;
    int highLength=0;
    //for every gene in the storageResource
    for (String gene: sr.data()){    
      //print all the Strings in sr that are longer than 60 characters
      if (gene.length() > 60){
       longThan++;
       System.out.println("genes that are longer than 60 characters are:" +gene);
      }
      //print the Strings in sr whose C-G-ratio is higher than 0.35
      if(cgRatio(gene)> 0.35){
        cgLongThan++;
        System.out.println("\ngenes that have a CG ratio longer than 0.35 are:" +gene);
      }
      //get the length of the longest gene in sr
      currLength=gene.length();
      if(currLength> highLength){
      highLength=currLength;
      }
    }
    //print the number of Strings in sr that are longer than 60 characters
    System.out.println("\nnumber of genes that are longer than 60 characters are:" + longThan);
    //print the number of strings in sr whose C-G-ratio is higher than 0.35
    System.out.println("\nnumber of genes that have cg ratio longer than 0.35 characters are:" + cgLongThan);
    System.out.println("\nlength of longest gene is :" +highLength);
  }

   public void test(){
     testOn("ATGCCATAG");
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
      part1 pr= new part1();
      pr.test();
   }
   //This method test processGenes
   public void testProcessGenes(){
     //create a new storageResource
     StorageResource sr=new StorageResource();
     //create a new url resource to open a url with the database for the project
     URLResource ur = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
     // FileResource method asString to convert the contents of the file to a single string so that you can use it like the other DNA strings you have been using.
     String dna = ur.asString();
     System.out.println(dna);
     System.out.println("dna length is" + dna.length());
     StorageResource sR= getAllGenes(dna);
     processGenes(sR);
     int countG= countGenes(dna);
     int countCTG=countCTG(dna);
     System.out.println("there is" + countG + "genes in" +dna);
     System.out.println("there is" + countCTG + "genes in" +dna);
   }
 
}
