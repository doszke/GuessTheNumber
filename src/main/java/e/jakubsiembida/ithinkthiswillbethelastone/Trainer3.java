package e.jakubsiembida.ithinkthiswillbethelastone;

import android.support.v7.app.AppCompatActivity;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Trainer3 {

    public static void main(String[] args) {


        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile("F:\\IThinkThisWillBeTheLastOne\\app\\src\\main\\res\\raw\\my_mlp2.nnet");

        DataSet dataSet = new DataSet(784, 10);


        int whichRow = 0;
        try (BufferedReader br = new BufferedReader(new FileReader((new File("C:\\Users\\Jakub Siembida\\Desktop\\MINST 20k-30k.txt"))))) {
            String sinput;
            System.out.println("start loading data");
            while (++whichRow < 10) {
                sinput = br.readLine();
                String[] line = sinput.split(",");
                double[] input = new double[784];
                double output[] = new double[10];


                for (int i = 0; i < line.length; i++) {
                    if (i < 784) {
                        input[i] = Double.valueOf(line[i]);
                    } else {
                        output[i - 784] = Double.valueOf(line[i]);
                    }
                }
/*
                String sth = "";
                for(double d : output){
                    sth += d + ",";
                }
                System.out.println(sth);
*/

                dataSet.addRow(new DataSetRow(input, output));
            }
            System.out.println("Loading data finished");
            for(DataSetRow r : dataSet.getRows()){
                System.out.println(Arrays.toString(r.getDesiredOutput()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(DataSetRow row : dataSet.getRows()){
            neuralNetwork.setInput(row.getInput());
            neuralNetwork.calculate();
            System.out.println(Arrays.toString(neuralNetwork.getOutput()));
            System.out.println(Arrays.toString(row.getDesiredOutput()));
            System.out.println();
            System.out.println();
        }



    }

}
