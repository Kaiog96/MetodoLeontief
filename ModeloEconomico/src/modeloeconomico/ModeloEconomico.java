/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloeconomico;

import java.util.Scanner;

/**
 *
 * @author cc10005060966
 */
public class ModeloEconomico {

    /**
     * @param A
     * @param b
     * @param args the command line arguments
     * @return 
     */
    public static double[] gauss(double[][] A, double[] b){
       
       //ETAPA DE ESCALONAMENTO
        for (int k = 0; k < A.length - 1; k++) {
            //procura o maior k-ésimo coeficiente em módulo
            double max = Math.abs(A[k][k]);
            int maxIndex = k;            
            for (int i = k + 1; i < A.length; i++) {
                if (max < Math.abs(A[i][k])) {
                    max = Math.abs(A[i][k]);
                    maxIndex = i;
                }
            }
            if (maxIndex != k) {
                /*
                troca a equação k pela equação com o
                maior k-ésimo coeficiente em módulo
                */
                for (int j = 0; j < A.length; j++) {
                    double temp = A[k][j];
                    A[k][j] = A[maxIndex][j];
                    A[maxIndex][j] = temp;
                }
                double temp = b[k];
                b[k] = b[maxIndex];
                b[maxIndex] = temp;
            }
            //Se A[k][k] é zero, então a matriz dos coeficiente é singular
            //det A = 0
            if (A[k][k] == 0) {
                return null;
            } else {
                //realiza o escalonamento
                for (int m = k + 1; m < A.length; m++) {
                    double F = -A[m][k] / A[k][k];
                    A[m][k] = 0; //evita uma iteração
                    b[m] = b[m] + F * b[k];
                    for (int l = k + 1; l < A.length; l++) {
                        A[m][l] = A[m][l] + F * A[k][l];
                    }
                }
            }
        }
        //ETAPA DE RESOLUÇÃO DO SISTEMA
        double[] X = new double[A.length];
        for (int i = A.length - 1; i >= 0; i--) {
            X[i] = b[i];
            for (int j = i + 1; j < A.length; j++) {
                X[i] = X[i] - X[j] * A[i][j];
            }
            X[i] = X[i] / A[i][i];
        }
        return X;
    }
    
    public static void main(String[] args) {
        
        // TODO code application logic here
        double matriz[][] = new double[3][3];
        Scanner ler = new Scanner(System.in);
        double resultado[] = new double[3];
        for(int i = 0; i<3 ; i++){
            
            for(int j = 0 ; j<3 ; j++){
                
                i++;
                j++;
                System.out.println("Adicione os valores da Matriz " + i + j);
                i--;
                j--;
                matriz[i][j] = ler.nextDouble();
                
                
            }
        }
        System.out.println("Resultados de Pesquisa");
        resultado[0] = ler.nextDouble();
        System.out.println("Resultados de Escrita");
        resultado[1] = ler.nextDouble();
        System.out.println("Resultados de Implementação");
        resultado[2] = ler.nextDouble();
        
        double[] x = gauss(matriz, resultado);
        System.out.printf(" x1 = %f \n x2 = %f \n x3 = %f \n" ,x[0], x[1] , x[2]);
    }
    
}
