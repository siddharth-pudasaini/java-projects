import java.util.Scanner;

public class Physics {

    public static void main(String[] args){

        Scanner scnr=new Scanner(System.in);

        for(int i=0;i<4;i++){
            System.out.println("Enter m1");
            double m1=scnr.nextDouble();
            System.out.println("Enter m2");
            double m2=scnr.nextDouble();
            System.out.println("Enter v1i");
            double v1i=scnr.nextDouble();
            System.out.println("Enter v2i");
            double v2i=scnr.nextDouble();
            System.out.println("Enter v1f");
            double v1f=scnr.nextDouble();
            System.out.println("Enter v2f");
            double v2f=scnr.nextDouble();

            double initialMomentum=m1*v1i+m2*v2i;
            double finalMomentum=m1*v1f+m2*v2f;

            double initialKE=0.5*(m1*v1i*v1i+m2*v2i*v2i);
            double finalKE=0.5*(m1*v1f*v1f+m2*v2f*v2f);

            double lostMomentum=((finalMomentum-initialMomentum)/initialMomentum)*100;
            double lostEnergy=((finalKE-initialKE)/initialKE)*100;

            System.out.printf("Percentage lost of momentum: ( %.3f - %.3f / %.3f ) * 100%% = %.3f%%\n",finalMomentum,initialMomentum,initialMomentum,lostMomentum);
            System.out.printf("Percentage lost of energy: ( %.3f - %.3f / %.3f ) * 100%% = %.3f%%\n",finalKE,initialKE,initialKE,lostEnergy);

//            System.out.printf("Initial Momentum: %.3f * %.3f + %.3f * %.3f = %.3f\n",m1,v1i,m2,v2i,initialMomentum);
//            System.out.printf("Final Momentum: %.3f * %.3f + %.3f * %.3f = %.3f\n",m1,v1f,m2,v2f,finalMomentum);
//
//            System.out.printf("Initial KE: 1/2 * (%.3f)* (%.3f)^2 + 1/2 (%.3f) * (%.3f)^2 = %.3f\n",m1,v1i,m2,v2i,initialKE);
//            System.out.printf("Initial KE: 1/2 * (%.3f)* (%.3f)^2 + 1/2 (%.3f) * (%.3f)^2 = %.3f\n",m1,v1f,m2,v2f,finalKE);

        }

    }






}
