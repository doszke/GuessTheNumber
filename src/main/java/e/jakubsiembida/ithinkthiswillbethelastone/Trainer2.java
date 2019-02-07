package e.jakubsiembida.ithinkthiswillbethelastone;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Trainer2 {



    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 784, 20, 10);
        DataSet dataSet = new DataSet(784, 10);

        int whichRow = 0;
        try (BufferedReader br = new BufferedReader(new FileReader((new File("C:\\Users\\Jakub Siembida\\Desktop\\MNIST 10k-20k.txt"))))) {
            String sinput;
            System.out.println("start loading data");
            int counter = 0;
            while (++counter < 4000) {
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



        long before = System.nanoTime();
        for(int i = 0; i < 6; i++) {
            System.out.println("Learning: " + i + 1);
            neuralNetwork.learn(dataSet);
        }
        long after = System.nanoTime();
        System.out.println("Learning session has been finished");

        System.out.println(after-before);

        neuralNetwork.save("F:\\IThinkThisWillBeTheLastOne\\app\\src\\main\\res\\raw\\my_mlp3.nnet");
        System.out.println("Network saved");


        testNeuralNetwork(neuralNetwork, dataSet);

        System.out.println(after-before);


    }


    public static void testNeuralNetwork(NeuralNetwork nnet, DataSet testSet) {

        for(DataSetRow dataRow : testSet.getRows()) {
            System.out.println();
            System.out.println("network validation test:");
            nnet.setInput(dataRow.getInput());
            nnet.calculate();
            double[] networkOutput = nnet.getOutput();
            System.out.print("Input: " + Arrays.toString(dataRow.getInput()));
            System.out.print("Output from dataset: " + Arrays.toString(dataRow.getDesiredOutput()));
            System.out.println(" Output: " + Arrays.toString(networkOutput));

        }

    }

}
