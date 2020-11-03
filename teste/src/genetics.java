import java.util.Random;

/*
* Falta definir os tamanhos dos vetores e as faixas dinâmicas de cada variável
* */

public class genetics {
    public static int[][] permutation(int[] crm1, int[] crm2){
        Random generator = new Random();
        int[][] nextGeneration = new int[10][6];

        for (int i = 0; i < 8; i++){
            int slicer = 3;
            double luck = generator.nextDouble();
            if (luck < 0.25){
                slicer = 2;
            }else if(slicer > 0.75){
                slicer = 4;
            }
            for (int j = 0; j < 6; j++){
                if (j < slicer){
                    nextGeneration[i][j] = crm1[j];
                }else {
                    nextGeneration[i][j] = crm2[j];
                }
            }
            luck = generator.nextDouble();
            boolean mutated = false;
            if (luck > 0.3) mutated = true;
            if (mutated){
                int position = generator.nextInt(6);
                int newValue;
                switch (position){
                    case 0:
                        newValue = generator.nextInt(701) + 200;
                        nextGeneration[i][position] = newValue;
                        break;
                    case 1:
                        newValue = generator.nextInt(601) + 200;
                        nextGeneration[i][position] = newValue;
                        break;
                    case 2:
                        newValue = generator.nextInt(501) + 200;
                        nextGeneration[i][position] = newValue;
                        break;
                    case 3:
                        newValue = generator.nextInt(301) + 200;
                        nextGeneration[i][position] = newValue;
                        break;
                    case 4:
                        newValue = generator.nextInt(401) + 200;
                        nextGeneration[i][position] = newValue;
                        break;
                    case 5:
                        newValue = generator.nextInt(201) + 200;
                        nextGeneration[i][position] = newValue;
                        break;
                }
            }
        }

        for (int i = 0; i < 6; i++ ){
            nextGeneration[8][i] = crm1[i];
            nextGeneration[9][i] = crm2[i];
        }

        return  nextGeneration;
    }
}
